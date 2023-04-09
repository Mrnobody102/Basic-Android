package com.student.manager;

import android.app.Application;

import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.ClassDAO;
import com.student.manager.dao.CourseDAO;
import com.student.manager.dao.LecturerClassSkillDAO;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.dao.MarkDAO;
import com.student.manager.dao.NotificationDAO;
import com.student.manager.dao.SlotDayDAO;
import com.student.manager.dao.StaffDAO;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.database.DatabaseDAO;
import com.student.manager.model.Account;
import com.student.manager.model.Course;
import com.student.manager.model.Lecturer;
import com.student.manager.model.Staff;
import com.student.manager.model.Student;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.util.DataUtil;
import com.student.manager.util.SaveUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SaveUtil.init(this);
        DatabaseDAO.init(this);
        initDataDAO();
    }

    private void initDataDAO() {
        DataUtil.init();
        AccountDAO.init(this);
        StudentDAO.init(this);
        LecturerDAO.init(this);
        StaffDAO.init(this);
        CourseDAO.init(this);
        ClassDAO.init(this);
        LecturerClassSkillDAO.init(this);
        StudentCourseSlotDayDAO.init(this);
        StudentClassDAO.init(this);
        SlotDayDAO.init(this);
        NotificationDAO.init(this);
        MarkDAO.init(this);
        randomData();
    }

    private void randomData() {

//         Account table
        if (AccountDAO.checkExit("student1") == null) {
            for (int i = 0; i < 90; i++) {
                AccountDAO.insertAccount(new Account("student" + i, "1",
                        "student", "Student" + i + "@gmail.com"));
                //         student table
                StudentDAO.insertStudent(new Student(
                        i + 1, "Student name" + i, i % 2, "2001/02/04",
                        "Ha Noi", "00xxx1234567", i % 3,
                        "2023/02/07", 1));
            }

        }
        if (AccountDAO.checkExit("lecturer1") == null) {
            for (int i = 0; i < 20; i++) {
                AccountDAO.insertAccount(new Account("lecturer" + i, "1",
                        "lecturer", "Lecturer" + i + "@gmail.com"));
                //         lecturer table
                LecturerDAO.insertLecturer(new Lecturer(
                        i + 1 + 90, "Lecturer name" + i, i % 2, "2001/02/04",
                        "Ha Noi", "00xxx1234567", "2023/02/07", 1));

            }
        }
        if (AccountDAO.checkExit("staff1") == null) {
            AccountDAO.insertAccount(new Account("s", "s", "staff",
                    "Staff1@gamil.com"));
            AccountDAO.insertAccount(new Account("staff2", "1", "staff",
                    "Staff2@gamil.com"));
            //         staff table
            for (int i = 0; i < 2; i++) {
                StaffDAO.insertStaff(new Staff(i + 1 + 110, "Staff name" + i, i % 2,
                        "2001/02/04", "Ha Noi", "00xxx1234567",
                        "2023/02/07", 1));
            }

        }

        if (CourseDAO.getCourseById(1) == null) {
            //         course table
            for (int i = 0; i < 3; i++) {
                CourseDAO.insertCourse(new Course("course" + (i + 1), "random course " + (i + 1),
                        i % 3 +1, 1));
            }


        }

        if(StudentCourseSlotDayDAO.getStudentCourseSlotDay(1,1,1)
                ==null){
            for (int i = 0; i < 90; i++) {
                if(i<30){
                    StudentCourseSlotDayDAO.insertStudentCourseSlotDay(
                            new StudentCourseSlotDay(i+1, 1, 1));
                }else if(i<60){
                    StudentCourseSlotDayDAO.insertStudentCourseSlotDay(
                            new StudentCourseSlotDay(i+1, 2, 2));
                }else{
                    StudentCourseSlotDayDAO.insertStudentCourseSlotDay(
                            new StudentCourseSlotDay(i+1, 3, 3));
                }
            }
        }
    }


}
