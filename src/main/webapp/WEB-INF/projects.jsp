<%--
  Created by IntelliJ IDEA.
  User: sharonmarroquin
  Date: 12/09/25
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://xmlns.jakarta.ee/jsf/html" prefix="h" %>
<%@ taglib uri="http://xmlns.jakarta.ee/jsf/core" prefix="f" %>
<%@ taglib uri="http://primefaces.org/ui" prefix="p" %>

<html>
<head>
    <title>Gestor de Proyectos</title>
</head>
<body>
<form id="formProjects">
    <p:growl id="msgs" showDetail="true" />

    <p:commandButton value="Nuevo Proyecto"
                     actionListener="#{projectController.newProject}"
                     oncomplete="PF('dlgProject').show()" update=":formProjectDlg" />

    <p:dataTable value="#{projectController.projects}" var="p" rowKey="#{p.id}">
        <p:column headerText="Nombre">#{p.name}</p:column>
        <p:column headerText="Owner">#{p.owner}</p:column>
        <p:column headerText="Estado">#{p.status}</p:column>
        <p:column>
            <p:commandButton value="Editar"
                             actionListener="#{projectController.setSelected(p)}"
                             oncomplete="PF('dlgProject').show()" update=":formProjectDlg" />
            <p:commandButton value="Eliminar"
                             actionListener="#{projectController.delete(p)}"
                             update="@form" />
            <p:commandButton value="Ver Tareas"
                             actionListener="#{taskController.loadTasks(p.id)}"
                             oncomplete="PF('dlgTasks').show()" update=":formTasksDlg" />
        </p:column>
    </p:dataTable>
</form>

<!-- Dialogo Proyecto -->
<p:dialog widgetVar="dlgProject" modal="true">
    <form id="formProjectDlg">
        <table>
            <tr>
                <td><label for="name">Nombre</label></td>
                <td><p:inputText id="name" value="#{projectController.selected.name}" required="true" /></td>
            </tr>
            <tr>
                <td><label for="owner">Owner</label></td>
                <td><p:inputText id="owner" value="#{projectController.selected.owner}" required="true" /></td>
            </tr>
            <tr>
                <td><label for="status">Estado</label></td>
                <td>
                    <p:selectOneMenu id="status" value="#{projectController.selected.status}">
                        <f:selectItems value="#{Project.Status.values()}" var="s" itemLabel="#{s}" itemValue="#{s}" />
                    </p:selectOneMenu>
                </td>
            </tr>
            <tr>
                <td><label for="desc">Descripción</label></td>
                <td><p:inputTextarea id="desc" value="#{projectController.selected.description}" /></td>
            </tr>
        </table>
        <p:commandButton value="Guardar" actionListener="#{projectController.save}"
                         update=":formProjects :msgs" oncomplete="PF('dlgProject').hide()" />
    </form>
</p:dialog>

<!-- Dialogo Tareas -->
<p:dialog widgetVar="dlgTasks" modal="true" header="Tareas del Proyecto">
    <jsp:include page="tasksbyproject.jsp"/>
</p:dialog>
</body>
</html>

