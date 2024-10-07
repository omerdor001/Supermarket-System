package PresentationLayer.GUI.HR.EmployeeInformationHR;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DriverModel {
    public List<EmployeeConstraintModel> constraints;
    public String employeeId;
    public String firstName;
    public String lastName;
    public String password;
    public int accountNumber;
    public int branchBankNumber;
    public int salary;
    public String termsOfEmployment;
    public String statusOfEmployee;
    public String phoneNumber;
    public RoleType type;
    @JsonDeserialize(using = JsonConverter.LocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonConverter.LocalDateTimeSerializer.class)
    public LocalDateTime hireDate;
    @JsonDeserialize(contentUsing = JsonConverter.LocalDateDeserializer.class)
    @JsonSerialize(contentUsing = JsonConverter.LocalDateSerializer.class)
    public LinkedList<LocalDate> deliveryDates;
    public LinkedList<String> qualifications;
    public int licenses;
    public List<String> roles;
    public boolean cancellations;
    public boolean management;
    public boolean logged;
    public int typeOrdinal;
    public boolean hrmanager;
    public DriverModel(@JsonProperty("constraints") List<EmployeeConstraintModel> constraints, @JsonProperty("employeeId") String employeeId, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("password") String password,
                       @JsonProperty("accountNumber") int accountNumber, @JsonProperty("branchBankNumber") int branchBankNumber, @JsonProperty("salary") int salary, @JsonProperty("termsOfEmployment") String termsOfEmployment,
                       @JsonProperty("statusOfEmployee") String statusOfEmployee, @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("type") RoleType type, @JsonProperty("hireDate") LocalDateTime hireDate,
                       @JsonProperty("deliveryDates") LinkedList<LocalDate> deliveryDates,@JsonProperty("qualifications") LinkedList<String> qualifications,@JsonProperty("licenses") int licenses,@JsonProperty("cancellation") boolean cancellation,@JsonProperty("roles") List<String> roles,
                       @JsonProperty("management") boolean management,@JsonProperty("logged") boolean logged,@JsonProperty("typeOrdinal") int typeOrdinal,@JsonProperty("hrmanager") boolean hrmanager){
        this.constraints=constraints;
        this.employeeId=employeeId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.accountNumber=accountNumber;
        this.branchBankNumber=branchBankNumber;
        this.salary=salary;
        this.termsOfEmployment=termsOfEmployment;
        this.statusOfEmployee=statusOfEmployee;
        this.phoneNumber=phoneNumber;
        this.type=type;
        this.hireDate=hireDate;
        this.deliveryDates=deliveryDates;
        this.qualifications=qualifications;
        this.licenses=licenses;
        this.cancellations=cancellation;
        this.roles=roles;
        this.management=management;
        this.logged=logged;
        this.typeOrdinal=typeOrdinal;
        this.hrmanager=hrmanager;
    }
}
