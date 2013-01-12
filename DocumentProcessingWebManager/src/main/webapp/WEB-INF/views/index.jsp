<%@ include file="/WEB-INF/views/head.jsp"%>
<title>ocrwebmanager</title>
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
		<div class="row-bot">
			<div class="row-bot-bg">
				<div class="main">
					<h2>
						Web based <span>Document Processing Manager</span>
					</h2>
				</div>
			</div>
		</div>
	</header>

	<!--==============================content================================-->
	<section id="content">
		<div class="ic"></div>
		<div class="main">
			<div class="wrapper"></div>
		</div>
		<div>
			<form:form modelAttribute="uploadItem" method="post"
				enctype="multipart/form-data" id="item">
				<table>
					<tr>
						<td><form:label for="fileData" path="fileData">
								<h3>Upload Images for processing</h3>
							</form:label></td>
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td><form:input path="fileData" type="file" /></td>
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Upload" /></td>

					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td><a class="button-1" href="ocr">Proceed to selecting
								executables</a></td>
					</tr>
				</table>
			</form:form>
		</div>
		<div>
			<table>
				<tr>
					<td><br /> <br /> <br /></td>
				</tr>

				<tr>
					<td><h3>In case you have added new executables or .xsd
							files after the server startup, click this button for changes to
							be visible</h3></td>
					<td><a class="button-1" href="/ocrwebmanager/reinitialize">Reinitialize</a></td>
				</tr>
			</table>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/footer.jsp"%>