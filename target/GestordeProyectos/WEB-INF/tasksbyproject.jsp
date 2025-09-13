<%--
  Created by IntelliJ IDEA.
  User: sharonmarroquin
  Date: 12/09/25
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://primefaces.org/ui" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<form id="formTasksDlg">
    <p:dataTable value="#{taskController.tasks}" var="t"
                 rowStyleClass="#{taskController.isOverdue(t) ? 'row-danger' : ''}">
        <p:column headerText="Título">#{t.title}</p:column>
        <p:column headerText="Prioridad">#{t.priority}</p:column>
        <p:column headerText="Fecha">#{t.dueDate}</p:column>
        <p:column headerText="Estado">
            <p:commandButton value="#{t.done ? 'Reabrir' : 'Completar'}"
                             actionListener="#{taskController.toggleDone(t)}"
                             update="@form" />
        </p:column>
        <p:column>
            <p:commandButton value="Editar"
                             actionListener="#{taskController.setSelected(t)}"
                             oncomplete="PF('dlgTask').show()" update=":formTaskDlg" />
            <p:commandButton value="Eliminar"
                             actionListener="#{taskController.delete(t)}"
                             update="@form" />
        </p:column>
    </p:dataTable>

    <p:commandButton value="Nueva Tarea"
                     actionListener="#{taskController.newTask(taskController.currentProjectId)}"
                     oncomplete="PF('dlgTask').show()" update=":formTaskDlg" />
</form>

<!-- Dialogo Tarea -->
<p:dialog widgetVar="dlgTask" modal="true">
    <form id="formTaskDlg">
        <table>
            <tr>
                <td><label for="title">Título</label></td>
                <td><p:inputText id="title" value="#{taskController.selected.title}" required="true" /></td>
            </tr>
            <tr>
                <td><label for="priority">Prioridad</label></td>
                <td>
                    <p:selectOneMenu id="priority" value="#{taskController.selected.priority}">
                        <f:selectItems value="#{Task.Priority.values()}" var="p" itemLabel="#{p}" itemValue="#{p}" />
                    </p:selectOneMenu>
                </td>
            </tr>
            <tr>
                <td><label for="date">Fecha Límite</label></td>
                <td><p:datePicker id="date" value="#{taskController.selected.dueDate}" /></td>
            </tr>
        </table>
        <p:commandButton value="Guardar" actionListener="#{taskController.save}"
                         update=":formTasksDlg :msgs" oncomplete="PF('dlgTask').hide()" />
    </form>
</p:dialog>
