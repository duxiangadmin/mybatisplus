package com.itlaiba.pojo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.itlaiba.mapper.EmployeeMapper;

public class t {
	private ApplicationContext iocContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	private EmployeeMapper mapper = iocContext.getBean("employeeMapper", EmployeeMapper.class);
	
	//添加单个实体
	@Test
	public void addemp() {
		Employee em = new Employee();
		em.setLastName("张三");
		em.setGender(1);
		mapper.insert(em);
	}
	
	//根据id删除单个
	@Test
	public void delete() {
		mapper.deleteById(6);
	}
	
//	根据id集合删除多个
	@Test
	public void deleteAll() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(5);arr.add(7);
		mapper.deleteBatchIds(arr);
	}
	
//	修改实体
	@Test
	public void update() {
		Employee em = new Employee();
		em.setId(5);
		em.setLastName("里斯2");
		em.setGender(1);
		mapper.updateById(em);
	}
	
	
	
//	查单个
	@Test
	public void selectbyid() {
		Employee employee = mapper.selectById(5);
		System.out.println(employee);
	}
	
//	查所有
	@Test
	public void selectbyAll() {
		List<Employee> list = mapper.selectList(null);
		System.out.println(list);
	}
	
//	条件查
	@Test
	public void selectbywhere() {
		List<Employee> list = mapper.selectList(new EntityWrapper<Employee>().eq("last_name", "张三"));
		System.out.println(list);
	}
	
//	分页查
	@Test
	public void selectpage() {
		List<Employee> list = mapper.selectPage(new RowBounds(1, 1), new EntityWrapper<Employee>().eq("last_name", "张三"));
		System.out.println(list);
	}
	
	
	@Test
	public void testEnvironment() throws Exception {
		DataSource ds = iocContext.getBean("dataSource", DataSource.class);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}
}
