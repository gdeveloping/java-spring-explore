package tech.gdev.javabasicexplore.javabasic;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaFileExplore {
    public static void main(String[] args) throws IOException {
        givenUnixSystem_whenCreatingFile_thenCreatedInPath();
    }

    static void givenUnixSystem_whenCreatingFile_thenCreatedInPath() throws IOException {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
        String fileName = "newFile.txt";
        Path pathToStore = fileSystem.getPath("");
        Path filePath = pathToStore.resolve(fileName);
        Files.createFile(filePath);
    }

}
