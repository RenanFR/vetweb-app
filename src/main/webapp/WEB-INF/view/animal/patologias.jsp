<%-- 
    Document   : patologias
    Created on : 22/02/2018, 20:32:49
    Author     : renan.rodrigues
--%>

<%@ taglib prefix="vetweb" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<vetweb:layout title="patologias">

    <jsp:attribute name="js">
        <script>
            $(document).ready(function(){
               $('#patologias').dataTable();
            });
        </script>         
    </jsp:attribute>
        
    <jsp:body>
    
        <button class="btn btn-primary">
            <i class="fa fa-save"></i>
            <a href="<c:url value="/animais/cadastroPatologia"></c:url>" style="color: white">   <spring:message code="cadastro"></spring:message></a>
        </button>    
        <table class="table table-responsive" id="patologias">
            <thead>
                <tr>
                    <th><spring:message code="patologias"></spring:message></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${patologias}" var="patologia">
                    <tr>
                        <td>
                            ${patologia.descricao}
                            <a href="<c:url value="/animais/atualizarPatologia/${patologia.nome}"></c:url>">
                                <i class="fa fa-pencil-square-o fa-2x"></i>
                            </a>
                            <a href="<c:url value="/animais/removerPatologia/${patologia.nome}"></c:url>">
                                <i class="fa fa-trash-o fa-2x"></i>
                            </a>                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </jsp:body>
    
</vetweb:layout>
