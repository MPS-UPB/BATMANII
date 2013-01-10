<%@ include file="/WEB-INF/views/head.jsp"%>
<title>Parameter Page</title>
<script>
	function verifySelect() {
		document.getElementById("idSelect").value = "";
	}
</script>
</head>
<body id="page2" onload="verifySelect();">
	<!--==============================header=================================-->
	<header>
		<div class="row-top">
			<div class="main">
				<div class="wrapper">
					<h1>
						<a href="#"><span>Batmanii</span></a>
					</h1>
				</div>
			</div>
		</div>
		<!-- <div class="row-bot">
        	<div class="row-bot-bg">
            	<div class="main">
                	<h2>Web based <span>Document Processing Manager</span></h2>
                </div>
            </div>
        </div>
         -->
	</header>

	<!--==============================content================================-->
	<section id="content">
		<div class="ic"></div>
		<div class="main">
			<div class="wrapper">
				Insert parameters for executable ${execName}
				<form:form method="post" action="doSave" modelAttribute="list">
					<table>
						<c:forEach items="${list.xmlElements}" var="xmlElement"
							varStatus="s">
							<tr>
								<c:if test="${xmlElement.level == 0}">
									<td hidden="true" colspan="${xmlElement.level }" align="center">${xmlElement.name}</td>
									<td hidden="true"><form:input type="text"
											path="xmlElements[${s.index}].value" /></td>

								</c:if>
								<c:if test="${xmlElement.level == 1}">
									<c:if
										test="${xmlElement.toDisplay == true && empty xmlElement.simpleType.enumeration}">
										<td colspan="${xmlElement.level }" align="left">${xmlElement.name}</td>
										<td><form:input type="text"
												path="xmlElements[${s.index}].value" /></td>
									</c:if>
									<c:if
										test="${xmlElement.toDisplay == true && not empty xmlElement.simpleType.enumeration}">
										<td colspan="${xmlElement.level }" align="left">${xmlElement.name}</td>
										<td><form:select id="mySelect"
												path="xmlElements[${s.index}].value">
												<c:forEach items="${xmlElement.simpleType.enumeration}"
													var="enum">
													<form:option value="${enum}" label="${enum}" />
												</c:forEach>
											</form:select></td>
									</c:if>
									<c:if test="${xmlElement.toDisplay == false}">
										<td colspan="${xmlElement.level}" align="left">${xmlElement.name}</td>
									</c:if>
								</c:if>
								<c:if test="${xmlElement.level > 1}">
									<c:if
										test="${xmlElement.toDisplay == true && empty xmlElement.enumeration}">
										<td colspan="${xmlElement.level }" align="right">${xmlElement.name}</td>
										<td><form:input type="text"
												path="xmlElements[${s.index}].value" /></td>
									</c:if>
									<c:if
										test="${xmlElement.toDisplay == true && not empty xmlElement.enumeration}">
										<td colspan="${xmlElement.level }" align="right">${xmlElement.name}</td>
										<td><form:select id="mySelect"
												path="xmlElements[${s.index}].value">
												<c:forEach items="${xmlElement.enumeration}" var="enum">
													<form:option value="${enum}" label="${enum}" />
												</c:forEach>
											</form:select></td>
										<td>Possible values are :</td>
										<c:forEach items="${xmlElement.enumeration}" var="enum">
											<td>${enum};</td>
										</c:forEach>
									</c:if>
									<c:if test="${xmlElement.toDisplay == false}">
										<td colspan="${xmlElement.level}" align="right">${xmlElement.name}</td>
									</c:if>
								</c:if>
								<form:hidden path="xmlElements[${s.index}].attribute" />
								<form:hidden path="xmlElements[${s.index}].level" />
								<form:hidden path="xmlElements[${s.index}].name" />
								<form:hidden path="xmlElements[${s.index}].enumeration" />
								<form:hidden path="xmlElements[${s.index}].minOccurs" />
								<form:hidden path="xmlElements[${s.index}].maxOccurs" />
								<!--<form:hidden path="xmlElements[${s.index}].simpleType" />-->
							<tr>
						</c:forEach>
						<tr>
							<td><input type="submit" value="Submit" /> <a
								href="/ocrwebmanager/ocr/cancel?execName=${exec.execName}">
									<input type="button" value="Back" />
							</a></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/footer.jsp"%>