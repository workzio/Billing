package com.java.business.HeadOffice.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.business.HeadOffice.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query(value = "  select a.*,al.*,m.memberid as employee_id,m.name,m.gender,m.aadharno from attendance as a"
			+ " join attendancelist as al on al.fk_attendance_id = a.attendance_id"
			+ "	join member as m on m.memberid = al.memberid", nativeQuery = true)
	List<Map<String, Object>> getAllMemberDetails();

	@Query(value = "select month(a.attendance_date) as month, year(attendance_date) as year,sum(al.present) as present_count,sum(al.absent) as absent_count,sum(al.full_day) as full_day,sum(al.half_day) as half_day"
			+ "	from attendance as a" + " join attendancelist as al on al.fk_attendance_id=a.attendance_id"
			+ " join member as m on m.memberid = al.memberid"
			+ "	where month(a.attendance_date) = month(curdate()) and year(a.attendance_date) = year(current_date()) and m.memberid=:memberid"
			+ "	group by  month(a.attendance_date) , year(attendance_date)", nativeQuery = true)
	List<Map<String, Object>> getAllMemberDetailsByMemberId(@Param("memberid") Long memberid);

	@Query(value = " select sum(al.present) as present_count, sum(al.absent) as absent_count,current_date() as today_date from attendance as a"
			+ " join attendancelist as al on al.fk_attendance_id= a.attendance_id"
			+ "	where a.attendance_date = current_date()", nativeQuery = true)
	List<Map<String, Object>> getAllMemberDetailsByMemberByDate();

	Optional<Attendance> findByAttendanceDate(Date attendanceDate);

}
