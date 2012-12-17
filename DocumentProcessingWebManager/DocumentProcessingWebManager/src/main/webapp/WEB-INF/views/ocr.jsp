<%@ include file="/WEB-INF/views/head.jsp"%>
<title>OCR Page</title>
<script type="text/javascript">
	function isChecked(checkboxId, execsId) {
		if (document.getElementById(checkboxId).checked == true) {
			document.getElementById(execsId).style.display = 'block';
			//document.getElementById('parameterseffect').style.display = 'block';
		} else {
			document.getElementById(execsId).style.display = 'none';
		}
	}
</script>
<script>
$(function() {
    // run the currently selected effect
    function runEffect() {
        var options = {};
        // run the effect
        $( "#effect" ).show( "blind", options, 500, callback );
    };

    //callback function to bring a hidden box back
    function callback() {
    };

    // set effect from select menu value
    $( "#preprocessing" ).click(function() {
        runEffect();
        return true;
    });

   // $( "#effectpreprocessing" ).hide();
    //$( "#effectpreprocessing" ).show("bounce");
    
});

</script>
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
							<div id="big">
								<ul style="display: none;" id="effectpreprocessing"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											preprocessing</h3></li>
									<li><input type="checkbox">preprocessing<br></li>
								</ul>

								<ul style="display: none;" id="effectbinarization"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											binarization</h3></li>
									<li><input type="checkbox">binarization<br></li>
								</ul>


								<ul style="display: none;" id="effectlayout"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											layout</h3></li>
									<li><input type="checkbox">layout<br></li>
								</ul>

								<ul style="display: none;" id="effectpaging"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											paging</h3></li>
									<li><input type="checkbox">paging<br></li>
								</ul>

								<ul style="display: none;" id="effectocr"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											ocr</h3></li>
									<li><input type="checkbox">ocr<br></li>
								</ul>

								<ul style="display: none;" id="effecthierarchy"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											hierarchy</h3></li>
									<li><input type="checkbox">hierarchy<br></li>
								</ul>

								<ul style="display: none;" id="effectpdf"
									class="ui-widget-content ui-corner-all">
									<li><h3 class="ui-widget-header ui-corner-all">Executables
											pdf-exporter</h3></li>
									<li><input type="checkbox">pdf<br></li>
								</ul>
								<!-- 
								<div class="toggler">
									<div id="effectp" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">Executables</h3>
										<c:forEach var="exec" items="${list}">
											<input type="checkbox">${exec}<br>
										</c:forEach>
									</div>
								</div> -->
								<!-- <div class="toggler">
									<div id="effect" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">Binarization</h3>
										<p>Aici vor fi listate executabilele de tipul binarization</p>
									</div>
								</div>
								<div class="toggler">
									<div id="effect" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">Layout</h3>
										<p>Aici vor fi listate executabilele de tipul layout</p>
									</div>
								</div>
								<div class="toggler">
									<div id="effect" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">Paging</h3>
										<p>Aici vor fi listate executabilele de tipul paging</p>
									</div>
								</div>
								<div class="toggler">
									<div id="effect" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">OCR</h3>
										<p>Aici vor fi listate executabilele de tipul ocr</p>
									</div>
								</div>
								<div class="toggler">
									<div id="effect" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">Hierarchy</h3>
										<p>Aici vor fi listate executabilele de tipul hierarchy</p>
									</div>
								</div>
								<div class="toggler">
									<div id="effect" class="ui-widget-content ui-corner-all">
										<h3 class="ui-widget-header ui-corner-all">PDF-exporter</h3>
										<p>Aici vor fi listate executabilele de tipul pdf-exporter</p>
									</div>
								</div>-->
							</div>
						</td>
						<td>
							<div class="parameter">
								<div id="parameterseffect" style="display: none;"
									class="ui-widget-content ui-corner-all">
									<h3 class="ui-widget-header ui-corner-all">Parameters</h3>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		</div>
	</section>
	<input id="selected" hidden="true" value="">
	<%@ include file="/WEB-INF/views/footer.jsp"%>