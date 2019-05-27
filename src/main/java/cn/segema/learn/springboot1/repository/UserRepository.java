package cn.segema.learn.springboot1.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.learn.springboot1.domain.User;
import cn.segema.learn.springboot1.vo.UserVO;

@CacheConfig(cacheNames = "userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>,JpaSpecificationExecutor<User> {

    @Cacheable(value = "getAllUsers",keyGenerator="keyGenerator2") 
    @Query(value = "SELECT a.* FROM tb_user a", nativeQuery = true)
    List<User> getAllUsers();
    
    @Cacheable(key ="#id")
    @Query(value = "select * from user where user_id =?1",nativeQuery = true)
    User findById(@Param("id") BigInteger id);
    
    @CachePut(key = "#id")
    @Query(value = "insert into tb_user(user_id,user_name,password) values(?1,?2,'123456')",nativeQuery = true)
    void createById(@Param("id")BigInteger id,@Param("name")String name);
    
    @CachePut(key = "#id")
    @Query(value = "update tb_user set user_name=?2 where user_id=:?1",nativeQuery = true)
    void updataById(BigInteger id,String name);
    
   // @CacheEvict(key ="#id",allEntries=true)
    @Query(value = "delete from tb_user where user_id=?1",nativeQuery = true)
    void deleteById(Long id);
    
    
    
    @Query(value = "SELECT * FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) and if(:#{#user.gender}!='',gender = :#{#user.gender},1=1) ORDER BY ?#{#pageable}",
               countQuery = "SELECT count(*) FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) and if(:#{#user.gender}!='',gender = :#{#user.gender},1=1)",
               nativeQuery = true)
    public Page<User> findByPage(@Param("user") UserVO user, Pageable pageable);
}