<%@ include file="/WEB-INF/views/head.jsp"%>
<title>OCR Page</title>
    <link href="/ocrwebmanager/resources/css/jquery-ui-1.8.14.custom.css" rel="stylesheet" type="text/css" />
    <script src="/ocrwebmanager/resources/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="/ocrwebmanager/resources/js/jquery/custom_jquery.js" type="text/javascript"></script>
    <script src="src/main/webapp/resources/js/jquery/ui.core.js" type="text/javascript"></script>

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
               	<h2>Web based <span>Document Processing Manager</span></h2>
			</div>
		</div>
	</div>
 	<div id="content">
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
	</div> 

    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"></div>
        <div class="main">
            <div class="wrapper">
    
            </div>
        </div>
    </section>
    
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	