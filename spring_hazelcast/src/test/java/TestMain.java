import com.sample.spring.domain.Employee;
import com.sample.spring.hazelcast.dao.HazelcastEmployeeDAO;
import com.sample.spring.hazelcast.service.HazelcastEmployeeService;
import com.sample.spring.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

import java.util.*;

public class TestMain {

    public static final int INSERT_COUNT = 1000;
    public static  void main(String[] args){

       ClassPathXmlApplicationContext  ctx = new ClassPathXmlApplicationContext("spring-config.xml");

        Map<Integer, Employee> employees = createEmployeesMap(100);

        //MYSQL SERVICE
        EmployeeService jdbcEmployeeService = (EmployeeService) ctx.getBean("JDBCEmployeeService");

        StopWatch stopWatch;
        /*stopWatch.start();
        jdbcEmployeeService.addEmployees(employees);
        stopWatch.stop();
        printTimes(jdbcEmployeeService, stopWatch);*/

        stopWatch = new StopWatch();
        stopWatch.start();
        jdbcEmployeeService.listEmployees();
        stopWatch.stop();
        printTimes(jdbcEmployeeService, stopWatch);

        EmployeeService hazelcastEmployeeService = (EmployeeService) ctx.getBean("HazelcastEmployeeService");
        /*stopWatch = new StopWatch();
        stopWatch.start();
        hazelcastEmployeeService.addEmployees(employees);
        stopWatch.stop();
        printTimes(hazelcastEmployeeService, stopWatch);*/

        stopWatch = new StopWatch();
        stopWatch.start();
        hazelcastEmployeeService.listEmployees();
        stopWatch.stop();
        printTimes(hazelcastEmployeeService, stopWatch);

        ((HazelcastEmployeeService)hazelcastEmployeeService).shutDown();

        ctx.close();
    }

    private static Map<Integer, Employee> createEmployeesMap(int startingID) {

        Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
        for (int i = 0; i < INSERT_COUNT; i++) {
            Employee employee = new Employee(startingID + i, "hazelcast_" + i, "Developer");
            employees.put(i, employee);
        }
        return employees;
    }

    private static void printTimes(EmployeeService employeeService, StopWatch stopWatch) {
        System.out.println(employeeService.getClass().getCanonicalName() + " total time = " + stopWatch.getTotalTimeSeconds() + " (secs)");
        System.out.println(employeeService.getClass().getCanonicalName() + " avg time = " + stopWatch.getTotalTimeMillis() / stopWatch.getTaskCount() + " (ms)");
    }
}
