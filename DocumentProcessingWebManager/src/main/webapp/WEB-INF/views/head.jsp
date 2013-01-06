<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
	<link rel="stylesheet" href="/ocrwebmanager/resources/css/reset.css"
		type="text/css" media="screen">
		<link rel="stylesheet" href="/ocrwebmanager/resources/css/style.css"
			type="text/css" media="screen">
			<script src="/ocrwebmanager/resources/js/cufon-yui.js"
				type="text/javascript"></script>
			<script src="/ocrwebmanager/resources/js/cufon-replace.js"
				type="text/javascript"></script>
			<script src="/ocrwebmanager/resources/js/Dynalight_400.font.js"
				type="text/javascript"></script>
			<link rel="stylesheet"
				href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
			<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
			<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
			<link rel="stylesheet" href="/resources/demos/style.css" />
			<style>
			
.big{
	width:100%;

}			
.types {
	width: 300px;
	position: relative;
	float: left;
}

#typeseffect {
	width: 240px;
	padding: 0.4em;
	position: relative;
}

#typeseffect h3 {
	margin: 0;
	padding: 0.4em;
	text-align: center;
}


.toggler {
	width: 300px;
	height: 240px;
	position: relative;
	float: center;
}

#button {
	padding: .5em 1em;
	text-decoration: none;
}

#effect {
	width: 300px;
	padding: 0.4em;
	position: relative;
}

#effectpreprocessing {
	width: 240px;
	padding: 0.4em;
	position: relative;
}
#effectbinarization {
	width: 240px;
	padding: 0.4em;
	position: relative;
}
#effectlayout {
	width: 240px;
	padding: 0.4em;
	position: relative;
}
#effectpaging {
	width: 240px;
	padding: 0.4em;
	position: relative;
}
#effectocr {
	width: 240px;
	padding: 0.4em;
	position: relative;
}
#effecthierarchy {
	width: 240px;
	padding: 0.4em;
	position: relative;
}
#effectpdf {
	width: 240px;
	padding: 0.4em;
	position: relative;
}

h3 {
	margin: 0;
	padding: 0.4em;
	text-align: center;
}


.parameters {
	width: 400px;
	position: relative;
	float: left;
}

#parameterseffect {
	width: 380px;
	padding: 0.4em;
	position: relative;
}

#parameterseffect h3 {
	margin: 0;
	padding: 0.4em;
	text-align: center;
}
</style>