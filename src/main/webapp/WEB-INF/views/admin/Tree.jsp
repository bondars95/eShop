<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>

		<meta http-equiv="Content-Type" content="text/html; 		charset=UTF-8">
		<title>Categories</title>
		
		<script type="text/JavaScript">
			// Add this to the onload event of the BODY element
			function addEvents() {
				activateTree(document.getElementById("LinkedList"));
			}
		
			// This function traverses the list and add links 
			// to nested list items
			function activateTree(oList) {
				// Collapse the tree
				for (var i = 0; i < oList.getElementsByTagName("ul").length; i++) {
					oList.getElementsByTagName("ul")[i].style.display = "none";
				}
				// Add the click-event handler to the list items
				if (oList.addEventListener) {
					oList.addEventListener("click", toggleBranch, false);
				} else if (oList.attachEvent) { // For IE
					oList.attachEvent("onclick", toggleBranch);
				}
				// Make the nested items look like links
				addLinksToBranches(oList);
			}
		
			// This is the click-event handler
			function toggleBranch(event) {
				var oBranch, cSubBranches;
				if (event.target) {
					oBranch = event.target;
				} else if (event.srcElement) { // For IE
					oBranch = event.srcElement;
				}
				cSubBranches = oBranch.getElementsByTagName("ul");
				if (cSubBranches.length > 0) {
					if (cSubBranches[0].style.display == "block") {
						cSubBranches[0].style.display = "none";
					} else {
						cSubBranches[0].style.display = "block";
					}
				}
			}
		
			// This function makes nested list items look like links
			function addLinksToBranches(oList) {
				var cBranches = oList.getElementsByTagName("li");
				var i, n, cSubBranches;
				if (cBranches.length > 0) {
					for (i = 0, n = cBranches.length; i < n; i++) {
						cSubBranches = cBranches[i].getElementsByTagName("ul");
						if (cSubBranches.length > 0) {
							addLinksToBranches(cSubBranches[0]);
							cBranches[i].className = "HandCursorStyle";
							cBranches[i].style.color = "blue";
							cSubBranches[0].style.color = "black";
							cSubBranches[0].style.cursor = "auto";
						}
					}
				}
			}
		</script>
	</head>
	<body onload="addEvents();">
	
		<div class="container">
			<h3>Tree of Categories</h3>
		</div>
		<div class="container" style="margin-bottom: 20px;">
			<button onclick="location.href = 'createCategory'"class="btn btn-primary">Create Main Node Category</button>
		</div>
		<table>
			<tr>
				<td>
					<form>
						<ul id="LinkedList" class="LinkedList">
							<c:forEach var="item" items="${categoryEditTree}">
								${item}
							</c:forEach>
						</ul>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>