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
			<div class="wrapper">
				${propertyHolder.inputSchemasFolder} <a class="button-1" href="#">Proba
					buton</a>
					<a class="button-1" href="ocr">OCR</a>
					<a class="button-1" href="/ocrwebmanager/ocr/reinitialize">Reinitializare</a>
			</div>
		</div>
		<form:form modelAttribute="uploadItem" method="post" enctype="multipart/form-data" id="item">
             <fieldset>
					<p>
						<form:label for="fileData" path="fileData">Upload Files</form:label><br/> 
						<form:input path="fileData" type="file"/>             
					</p>
					
					<p>
						<input type="submit" value="Upload" />
					</p>               
			</fieldset>         
		</form:form> 
	</section>

	<%@ include file="/WEB-INF/views/footer.jsp"%>