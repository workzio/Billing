package com.java.business.HeadOffice.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.business.HeadOffice.entity.Product;
import com.java.business.HeadOffice.entity.Purchase;
import com.java.business.HeadOffice.entity.Receipt;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryid(long categoryid);

	@Query(value = "select * from ("
			+ "select p.productid, p.productname, c.category,c.category_id,u.unit_id,u.unitname, p.hsn_code, p.stock, p.productdescription, p.tax_percentage, "
			+ "(select sum(shipped_qty) from purchaselist pl where pl.productid = p.productid group by productid) stock_in, "
			+ "(select sum(shipped_qty) from saleslist as sl where sl.productid = p.productid group by productid) stock_out "
			+ "from product p " + "join category c on p.categoryid = c.category_id "
			+ "join unit u on p.unit_id = u.unit_id) as subquery "
			+ "order by subquery.productid desc", nativeQuery = true)
	List<Map<String, Object>> getProductView();

	@Query(value = "SELECT * FROM (SELECT p.categoryid, c.category, p.productid, p.productname, p.stock, p.tax_percentage FROM product AS p JOIN category AS c ON p.categoryid = c.category_id) AS subquery ORDER BY subquery.productid DESC", nativeQuery = true)
	List<Map<String, Object>> findStock();

	@Query(value = "select sum(case when stock >= 0 then stock else 0 end) as stock from product", nativeQuery = true)
	List<Map<String, Object>> getTotalStock();

	@Query(value = "    select p.productid,p.productname,c.category,u.unitname,"
			+ "    (SELECT SUM(shipped_qty) FROM saleslist as sl WHERE sl.productid = p.productid GROUP BY productid) moving_stock"
			+ "    from product as p" + "    join category as c on c.category_id = p.categoryid"
			+ "    join unit as u on u.unit_id = p.unit_id" + "    order by moving_stock desc", nativeQuery = true)
	List<Map<String, Object>> getMovingProduct();

	@Query(value = "select sum(sl.shipped_qty) as moving_stock, p.productid, p.productname, c.category, u.unitname, s.invoice_date "
			+ "from product as p " + " join category as c on c.category_id = p.categoryid "
			+ " join unit as u on u.unit_id = p.unit_id " + " join saleslist as sl on sl.productid = p.productid "
			+ " join sales as s on s.sales_id = sl.fk_sales_id "
			+ " where s.invoice_date between :startDate and :endDate "
			+ " group by p.productid, p.productname, c.category, u.unitname, s.invoice_date "
			+ " order by moving_stock desc", nativeQuery = true)
	List<Map<String, Object>> getAllHighMovingProducts(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

}
