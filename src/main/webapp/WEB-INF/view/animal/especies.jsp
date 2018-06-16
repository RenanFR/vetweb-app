<%-- 
    Document   : especies
    Created on : 11/02/2018, 22:43:34
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<vetweb:layout title="especies">

    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#especies').dataTable();
            });
        </script>         
    </jsp:attribute>
        
    <jsp:body>
    
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/animais/cadastroEspecie"></c:url>" style="color: white">   <spring:message code="cadastro"></spring:message></a>
        </button>    
        <table class="table table-responsive" id="especies">
            <thead>
                <tr>
                    <th><spring:message code="especie"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${especies}" var="especie">
                    <tr>
                        <td>
                            ${especie.descricao}
                            <a href="<c:url value="/animais/atualizarEspecie/${especie.especieId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/animais/removerEspecie/${especie.especieId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                      
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <span class="label label-danger">
            ${mensagemErro}
        </span>
        
    </jsp:body>
    
</vetweb:layout>