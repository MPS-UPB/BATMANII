<%@ include file="/WEB-INF/views/head.jsp"%>
<title>Result Page</title>
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
    </header>
    
	<!--==============================content================================-->
    <section id="content"><div class="ic"></div>
        <div class="main">
            <div class="wrapper">
            <!--==============================Andrei================================-->
				<table>
					<c:forEach var="fileName" items="${list}">
						<tr>
							<td><a href="/ocrwebmanager/result/download?fileName=${fileName}">${fileName}</a></td>
							<!--
							<td><a href="/ocrwebmanager/result/download?fileName=${fileName}">
								<input type="button" value="Download"></a></td>
							-->
						</tr>
					</c:forEach>
				</table>    
			<!--==============================ANDREI================================-->			
            </div>
        </div>
    </section>
    
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	