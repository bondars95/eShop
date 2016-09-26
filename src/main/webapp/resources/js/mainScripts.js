	// Add this to the onload event of the BODY element
	function addEvents() {
		activateTree(document.getElementById("LinkedList1"));
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
	
		function showByCategory(category) {
		 var category = category;
		 $.ajax({
		  url : 'categories', //call method to save to basket
		  type: 'GET',
		  /* dataType: 'json',
		  contentType: 'application/json',
		     mimeType: 'application/json', */
		  data : ({
		   categoryName: category
		  }),
		  success: function () { // everything works but can't reach success
		   var firstHalf = "cartButton";
		   var name = firstHalf.concat(productid);
		   var anchor = document.getElementById(name);
		   anchor.text = "added";
		   anchor.href = "javascript:void(0)";
		  }
		 });
		} 
