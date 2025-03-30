package tech.gdev.springbasicexplore.jdbc.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.gdev.springbasicexplore.jdbc.entity.TestCode;

import java.util.List;

/**
 * @author gdev
 * @date 2025/3/30 16:14
 */
@Mapper
public interface TestCodeMapper {

    @Insert("INSERT INTO test_code(id, code, note) VALUES(#{id}, #{code}, #{note})")
    int insert(TestCode testCode);

    @Select("SELECT * FROM test_code WHERE id = #{id}")
    TestCode selectById(int id);

    TestCode selectByCode(int code);

    List<TestCode> selectAll();

    @Update("UPDATE test_code SET code=#{code}, note=#{note} WHERE id=#{id}")
    int updateById(TestCode testCode);

    @Update("UPDATE test_code SET code=#{code} WHERE id=#{id}")
    int updateCodeById(TestCode testCode);

    @Delete("DELETE FROM test_code WHERE id = #{id}")
    int deleteById(int id);
}
