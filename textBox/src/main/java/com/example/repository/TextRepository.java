package com.example.repository;

import com.example.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


/**
 * @author ：linhui
 * @date ：2019/06/27  16:40
 * @description：文本盒dao层
 */

public interface TextRepository extends JpaRepository<Text,String> ,Serializable {

}
