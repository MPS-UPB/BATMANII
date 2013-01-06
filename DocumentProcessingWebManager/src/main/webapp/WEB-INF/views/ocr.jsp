<%@ include file="/WEB-INF/views/head.jsp"%>
<title>OCR Page</title>
<script type="text/javascript">
	function isChecked(checkboxId, execsId) {
		if (document.getElementById(checkboxId).checked == true) {
			document.getElementById("effect").style.display = 'block';
			document.getElementById(execsId).style.display = 'block';
		} else {
			document.getElementById(execsId).style.display = 'none';
			if (document.getElementById("preprocessing").checked == false
					&& document.getElementById("binarization").checked == false
					&& document.getElementById("layout").checked == false
					&& document.getElementById("paging").checked == false
					&& document.getElementById("ocr").checked == false
					&& document.getElementById("hierarchy").checked == false
					&& document.getElementById("pdf-exporter").checked == false)
				document.getElementById("effect").style.display = 'none';
		}
	}

	function verify() {
	}
</script>
</head>
<body id="page2" onload="verify();">
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
					<h2>
						Web based <span>Document Processing Manager</span>
					</h2>
				</div>
			</div>
		</div> -->
	</header>

	<!--==============================content================================-->
	<section id="content">
		<div class="ic"></div>
		<div class="main">
			<div class="big">
				<table>
					<tr>
						<td>
							<div class="types">
								<div id="typeseffect" class="ui-widget-content ui-corner-all">

									<h3 class="ui-widget-header ui-corner-all">Types</h3>

									<input type="checkbox" value="Preprocessing" id="preprocessing"
										onclick="isChecked('preprocessing', 'effectpreprocessing')">Preprocesssing<br>
									<input type="checkbox" value="Binarization" id="binarization"
										onclick="isChecked('binarization', 'effectbinarization')">Binarization<br>
									<input type="checkbox" value="layout" id="layout"
										onclick="isChecked('layout', 'effectlayout')">Layout<br>
									<input type="checkbox" value="Paging" id="paging"
										onclick="isChecked('paging', 'effectpaging')">Paging<br>
									<input type="checkbox" value="OCR" id="ocr"
										onclick="isChecked('ocr', 'effectocr')">OCR<br> <input
										type="checkbox" value="Hierarchy" id="hierarchy"
										onclick="isChecked('hierarchy', 'effecthierarchy')">Hierarchy<br>
									<input type="checkbox" value="PDF-exporter" id="pdf-exporter"
										onclick="isChecked('pdf-exporter','effectpdf')">PDF-exporter<br>
								</div>
							</div>
						</td>
						<td>
							<div id="effect" style="display: none">
								<ul id="effectpreprocessing" style="display: none"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											preprocessing</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"preprocessing\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>

								</ul>

								<ul id="effectbinarization" style="display: none"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											binarization</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"binarization\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>
								</ul>


								<ul id="effectlayout" class="ui-widget-content ui-corner-all"
									style="display: none">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											layout</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"layout\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>
								</ul>

								<ul id="effectpaging" class="ui-widget-content ui-corner-all"
									style="display: none">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											paging</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"paging\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>
								</ul>

								<ul id="effectocr" class="ui-widget-content ui-corner-all"
									style="display: none">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											ocr</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"ocr\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>
								</ul>

								<ul id="effecthierarchy" class="ui-widget-content ui-corner-all"
									style="display: none">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											hierarchy</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"hierarchy\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>
								</ul>

								<ul id="effectpdf" class="ui-widget-content ui-corner-all"
									style="display: none">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											pdf-exporter</h3></li>
									<c:forEach var="exec" items="${execs}">
										<c:if test="${exec.execType==\"pdf\"}">
											<li><a
												href="/ocrwebmanager/ocr/parameter?execName=${exec.execName}&execType=${exec.execType}"><input
													type="button" value="${exec.execName}" title="Add/Modify"></a><br></li>
										</c:if>
									</c:forEach>
								</ul>

							</div>
						</td>
						<td>
							<div class="parameter">
								<div id="parameterseffect" style="display: block;"
									class="ui-widget-content ui-corner-all">
									<h3 class="ui-widget-header ui-corner-all">Selected
										executables</h3>
									<table>
										<c:forEach var="selexec" items="${selectedExecs}">
											<tr>
												<td>${selexec.execName}</td>
												<td>->Type ${selexec.execType}</td>
												<td><a
													href="/ocrwebmanager/ocr/parameter?execName=${selexec.execName}&execType=${exec.execType}"><input
														type="button" value="Modify"></a></td>
												<td><a
													href="/ocrwebmanager/ocr/delete?execName=${selexec.execName}&execType=${exec.execType}"><input
														type="button" value="Delete"></a></td>

											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>	<a class="button-1" href="/ocrwebmanager/result">Process</a>
	
		</div>
	</section>
	<input id="selectedExecs" hidden="true" value="${selectedExecs}"></input>
	<%@ include file="/WEB-INF/views/footer.jsp"%>