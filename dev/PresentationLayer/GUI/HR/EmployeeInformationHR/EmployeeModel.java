package PresentationLayer.GUI.HR.EmployeeInformationHR;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.List;

enum RoleType {BRANCHEMPLOYEE, DRIVER, HRMANAGER, DELIVERYMANAGER, SUPPLIERMANAGER,STORAGEMANAGER}

public class EmployeeModel {
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
    public boolean management;
    public boolean hrmanager;
    public boolean cancellations;
    public List<String> roles;
    public boolean logged;
    public int typeOrdinal;

    public EmployeeModel(@JsonProperty("constraints") List<EmployeeConstraintModel> constraints,@JsonProperty("employeeId") String employeeId,@JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("password") String password,
                         @JsonProperty("accountNumber") int accountNumber,@JsonProperty("branchBankNumber") int branchBankNumber,@JsonProperty("salary") int salary,@JsonProperty("termsOfEmployment") String termsOfEmployment,
                         @JsonProperty("statusOfEmployee") String statusOfEmployee, @JsonProperty("phoneNumber") String phoneNumber,@JsonProperty("type") RoleType type,@JsonProperty("hireDate") LocalDateTime hireDate,
                         @JsonProperty("management") boolean management,@JsonProperty("hrmanager") boolean hrmanager,@JsonProperty("cancellations") boolean cancellations,@JsonProperty("roles") List<String> roles,@JsonProperty("logged") boolean logged,@JsonProperty("typeOrdinal") int typeOrdinal){
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
        this.management=management;
        this.hrmanager=hrmanager;
        this.cancellations=cancellations;
        this.roles=roles;
        this.logged=logged;
        this.typeOrdinal=typeOrdinal;

    }
}
