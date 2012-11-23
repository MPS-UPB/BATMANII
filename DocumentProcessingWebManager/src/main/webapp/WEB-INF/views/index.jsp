<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>OCR WEB MANAGER</title>
</head>
<body>

	<h1>Welcome user</h1>
	<h2>Input schemas folder is ${propertyHolder.inputSchemasFolder}</h2>
	<h2>Output schemas folder is ${propertyHolder.outputSchemasFolder}</h2>
	<h2>Execs folder is ${propertyHolder.execsFolder}</h2>
	<h2>Uploaded images folder is ${propertyHolder.uploadedImagesFolder}</h2>
</body>
</html>