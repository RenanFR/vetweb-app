<%-- 
    Document   : racas
    Created on : 14/02/2018, 17:35:16
    Author     : renan.rodrigues
--%>
<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="racas">

    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#racas').dataTable();
            });
        </script>         
    </jsp:attribute>
        
    <jsp:body>
    
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/animais/cadastroRaca"></c:url>" style="color: white">   <spring:message code="cadastro"></spring:message></a>
        </button>    
        <table class="table table-responsive" id="racas">
            <thead>
                <tr>
                    <th>Ra�a</th>
                    <th>Esp�cie</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${racas}" var="raca">
                    <tr>
                        <td>
                            ${raca.descricao}
                            <a href="<c:url value="/animais/atualizarRaca/${raca.racaId}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>     
                            <a href="<c:url value="/animais/removerRaca/${raca.racaId}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                            
                        </td>
                        <td>
                            ${raca.especie.descricao}
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