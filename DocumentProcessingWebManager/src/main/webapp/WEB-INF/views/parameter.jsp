<%@ include file="/WEB-INF/views/head.jsp"%>
<title>Parameter Page</title>
</head>
<body id="page2">
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
								<c:if test="${xmlElement.toDisplay == true}">
									<td colspan="${xmlElement.level }" align="center">${xmlElement.name}</td>
									<td><form:input type="text" path="xmlElements[${s.index}].value" /></td>
								</c:if>
								<c:if test="${xmlElement.toDisplay == false}">
									<td colspan="${xmlElement.level }" align="center">${xmlElement.name}</td>
								</c:if>
								<td><form:hidden path="xmlElements[${s.index}].attribute" />
									<form:hidden path="xmlElements[${s.index}].level" /> <form:hidden
										path="xmlElements[${s.index}].name" />
							<tr>
						</c:forEach>
					</table>
					<input type="submit" value="Submit" />
				</form:form>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/footer.jsp"%>