package org.jakartaeerecipe.chapter08.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.EmployeeSession;
import org.jakartaeerecipe.entity.Employee;

import java.util.List;

@Named(value = "employeeControllerFinal")
@RequestScoped
public class EmployeeController {

    @EJB
    EmployeeSession ejbFacade;

    private List<Employee> employeeList;

    /**
     * Creates a new instance of EmployeeController
     */
    public EmployeeController() {
    }

    @PostConstruct
    public void init(){
        setEmployeeList(ejbFacade.findAll());
    }


    public List getActiveEmployeeCount(){
        return ejbFacade.obtainActiveEmployeeCount();
    }

    public void inactivateEmployees(){
        String message = ejbFacade.updateEmployeeStatusActive();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        setEmployeeList(ejbFacade.findAll());
    }

    public void activateEmployees(){
        String message = ejbFacade.updateEmployeeStatusInactive();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        setEmployeeList(ejbFacade.findAll());
    }

    /**
     * @return the employeeList
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
