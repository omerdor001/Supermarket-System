package PresentationLayer.GUI.HR.UpdateEmployeeDetails;

import PresentationLayer.GUI.HR.EmployeeInformationHR.BranchEmployeeModel;
import PresentationLayer.GUI.HR.RegisterEmployee.REView;
import ServiceLayer.JsonService.JsonConverter;
import ServiceLayer.SystemFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UEController implements ActionListener {

    public UEView ueView;
    SystemFacade systemFacade;

    public UEController(String id) {
        ueView = new UEView();
        systemFacade = SystemFacade.getInstance();
        ueView.createMenu(id);
        ueView.getFrame().setVisible(true);
        ueView.submitChanges.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = ueView.idTB.getText();
        String employeeType = ueView.comboBoxEmployeeTypes.getSelectedItem().toString();
        String textInfoChange = ueView.newInfoTB.getText();
        String textInfoDetailDriver = ueView.comboBoxDriver.getSelectedItem().toString();
        String textInfoDetailBranchEmployee = ueView.comboBoxBranchEmployee.getSelectedItem().toString();
        String textInfoDetailHRManager = ueView.comboBoxHRManager.getSelectedItem().toString();
        String addOrRemove = ueView.comboBoxAddRemove.getSelectedItem().toString();
        String role = ueView.comboBoxRoles.getSelectedItem().toString();
        String resultB = systemFacade.showEmployee(id);
        BranchEmployeeModel branchEmployeeModel;
        String message;
        if (e.getSource() == ueView.submitChanges) {
            try {
                if (systemFacade.isEmployeeExists(id).equals("false")) {
                    JOptionPane.showMessageDialog(null, "Employee id does not exists", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                } else if (employeeType.equals("Branch Employee")) {
                    try {
                        branchEmployeeModel = JsonConverter.fromJson(resultB, BranchEmployeeModel.class);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (systemFacade.isBranchEmployeeV(id).equals("false")) {
                        JOptionPane.showMessageDialog(null, "Employee is not a branch employee", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("First name")) {
                        message = systemFacade.setFirstName(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "First name was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Last name")) {
                        message = systemFacade.setLastName(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Last name was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Password")) {
                        message = systemFacade.editPassword(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Password was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Account number")) {
                        message = systemFacade.setAccountNumber(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Account number was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Branch bank account")) {
                        message = systemFacade.setBranchBankNumber(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Branch bank account was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Salary")) {
                        message = systemFacade.setSalary(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Salary was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Terms of employment")) {
                        message = systemFacade.setTermsOfEmployment(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Terms of employment was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Status of employee")) {
                        message = systemFacade.setStatusOfEmployee(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Status of employee was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Phone number")) {
                        message = systemFacade.setPhoneNumber(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Phone number was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("HR Manager")) {
                        message = systemFacade.setHRManager(id);
                        if (message.equals("Employee's HR status was edited successfully")) {
                            JOptionPane.showMessageDialog(null, "HR Manager option was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Cancellation option")) {
                        if (branchEmployeeModel.cancellations && addOrRemove.equals("Add")) {
                            JOptionPane.showMessageDialog(null, "Employee already has a cancellation option", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                        } else if (!branchEmployeeModel.cancellations && addOrRemove.equals("Remove")) {
                            JOptionPane.showMessageDialog(null, "Employee does not have a cancellation option", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                        } else {
                            message = systemFacade.setCancellation(id);
                            if (message.equals("Cancellation permission was changed successfully")) {
                                JOptionPane.showMessageDialog(null, "Cancellation option was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (textInfoDetailBranchEmployee.equals("Management option")) {
                        if (branchEmployeeModel.management && addOrRemove.equals("Add")) {
                            JOptionPane.showMessageDialog(null, "Employee already has a management option", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                        } else if (!branchEmployeeModel.management && addOrRemove.equals("Remove")) {
                            JOptionPane.showMessageDialog(null, "Employee does not have a management option", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                        } else {
                            message = systemFacade.setManagement(id);
                            if (message.equals("success")) {
                                JOptionPane.showMessageDialog(null, "Management option was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (textInfoDetailBranchEmployee.equals("Insert role")) {
                        message = systemFacade.insertRole(id, role);
                        if (message.equals("Role inserted successfully")) {
                            JOptionPane.showMessageDialog(null, "Role added successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailBranchEmployee.equals("Remove role")) {
                        message = systemFacade.removeRole(id, role);
                        if (message.equals("Role removed successfully")) {
                            JOptionPane.showMessageDialog(null, "Role removed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (employeeType.equals("Driver")) {
                    if (systemFacade.isDriverV(id).equals("false")) {
                        JOptionPane.showMessageDialog(null, "Employee is not a driver", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("First name")) {
                        message = systemFacade.setFirstName(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "First name was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Last name")) {
                        message = systemFacade.setLastName(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Last name was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Password")) {
                        message = systemFacade.editPassword(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Password was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Account number")) {
                        message = systemFacade.setAccountNumber(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Account number was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Branch bank account")) {
                        message = systemFacade.setBranchBankNumber(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Branch bank account was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Salary")) {
                        message = systemFacade.setSalary(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Salary was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Terms of employment")) {
                        message = systemFacade.setTermsOfEmployment(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Terms of employment was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Status of employee")) {
                        message = systemFacade.setStatusOfEmployee(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Status of employee was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Phone number")) {
                        message = systemFacade.setPhoneNumber(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Phone number was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Set licenses")) {
                        message = systemFacade.upgradeDriverLicense(id, Integer.parseInt(textInfoChange));
                        if (message.equals("Driver license changed successfully")) {
                            JOptionPane.showMessageDialog(null, "Licenses was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Add qualification")) {
                        message = systemFacade.addDriverQualification(id, textInfoChange);
                        JOptionPane.showMessageDialog(null, "Qualification added successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        if (message.equals("Driver qualification added successfully")) {
                            JOptionPane.showMessageDialog(null, "Qualification added successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("Remove qualification")) {
                        message = systemFacade.removeDriverQualification(id, textInfoChange);
                        if (message.equals("Driver qualification removed successfully")) {
                            JOptionPane.showMessageDialog(null, "Qualification removed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailDriver.equals("HR Manager")) {
                        message = systemFacade.setHRManager(id);
                        if (message.equals("Employee's HR status was edited successfully")) {
                            JOptionPane.showMessageDialog(null, "HR Manager option was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (employeeType.equals("HR Manager")) {
                    if (systemFacade.isHRManager(id).equals("false")) {
                        JOptionPane.showMessageDialog(null, "Employee is not HR Manager", "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("First name")) {
                        message = systemFacade.setFirstName(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "First name was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Last name")) {
                        message = systemFacade.setLastName(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Last name was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Password")) {
                        message = systemFacade.editPassword(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Password was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Account number")) {
                        message = systemFacade.setAccountNumber(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Account number was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Branch bank account")) {
                        message = systemFacade.setBranchBankNumber(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Branch bank account was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Salary")) {
                        message = systemFacade.setSalary(id, Integer.parseInt(textInfoChange));
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Salary was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Terms of employment")) {
                        message = systemFacade.setTermsOfEmployment(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Terms of employment was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Status of employee")) {
                        message = systemFacade.setStatusOfEmployee(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Status of employee was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    } else if (textInfoDetailHRManager.equals("Phone number")) {
                        message = systemFacade.setPhoneNumber(id, textInfoChange);
                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Phone number was changed successfully", "UpdateEmployeeDetailsSuccess", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, message, "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "UpdateEmployeeDetailsError", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
