package tech.gdev.httpserver;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gdev
 * @date 2025/10/1 21:57
 */
public class HttpServerThreadPool {

    public static void main(String[] args) throws Exception {
        HttpServerThreadPool httpServer = new HttpServerThreadPool();
        httpServer.start();
    }

    private final ExecutorService SERVICE = Executors.newFixedThreadPool(5);

    public void start() throws Exception {
        //创建ServerSocketChannel，监听8080端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        //设置为非阻塞模式
        ssc.configureBlocking(false);
        //为ssc注册选择器
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //创建处理器
        while (true) {
            // 等待请求，每次等待阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传参数将一直阻塞
            if (selector.select(3000) == 0) {
                continue;
            }
            // 获取待处理的SelectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                // 启动新线程处理SelectionKey
                if (key.isAcceptable()) {
                    accept(key, selector);
                } else {
                    if (!key.isReadable()) {
                        System.out.println("Key is not read able.");
                        continue;
                    }
                    // 把请求数据的通道提交给线程池处理
                    SERVICE.submit(new NioServerHandler((SocketChannel) key.channel()));
                    // 该 Client 请求提交给客户端后，key.cancel 可以解除监听
                    key.cancel();
                    System.out.println("Submit task and cancel this SelectionKey.");
                }
                // 处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
                keyIter.remove();
            }
        }
    }

    public void accept(SelectionKey key, Selector selector) {
        try {
            // ServerSocketChannel 监听到了 Accept 事件后的处理过程，从通道中获取 SocketChannel
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            // 注册客户端 Channel 的读事件，因为注册的通道对象不一样了，是收到的 Socket 对象
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("Start to process accepted socket.");
            // 打印客户端地址
            String clientInfo = socketChannel.socket().getInetAddress().getHostAddress();
            int portInfo = socketChannel.socket().getPort();
            System.out.println("Receive client info " + clientInfo + ", portInfo:" + portInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class NioServerHandler implements Runnable {
        SocketChannel socketChannel;

        public NioServerHandler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                socketChannel.read(buffer);
                buffer.flip(); // 切换为读模式
                byte[] data = new byte[buffer.remaining()]; // 只分配实际读取的数据大小
                buffer.get(data); // 将有效数据复制到新数组
                String reqMsg = new String(data, StandardCharsets.UTF_8);
                buffer.clear(); // 清空缓冲区准备下一次读取
                socketChannel.write(getOutBuffer(StandardCharsets.UTF_8.displayName(), reqMsg));
                System.out.println();
                System.out.println("接收到 client request:\n" + reqMsg);
                System.out.println("response finished");
                socketChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ByteBuffer getOutBuffer(String localCharset, String reqMsg) throws UnsupportedEncodingException {
        StringBuilder sendString = new StringBuilder();
        sendString.append("HTTP/1.1 200 OK\r\n");
        //响应报文首行，200表示处理成功
        sendString.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
        sendString.append("\r\n");
        // 报文头结束后加一个空行
        sendString.append("<html><head><title>显示报文</title></head><body>");
        sendString.append("接收到请求报文是：<br/>");
        for (String s : reqMsg.split("\r\n")) {
            sendString.append(s + "<br/>");
        }
        sendString.append("</body></html>");
        ByteBuffer outBuffer = ByteBuffer.wrap(sendString.toString().getBytes(localCharset));
        return outBuffer;
    }
}