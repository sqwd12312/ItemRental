var deleteByid = function(id, url, pageindex, errorMessage) {
	$.jq_Confirm( {
		message : "确定要删除吗?",
		btnOkClick : function() {
			$.ajax( {
				url : url,
				data : {
					id : id
				},
				success : function(e) {
					if (e > 0) {
						$.jq_Alert( {
							message:"删除成功"
						});
						if (pageindex != undefined) {
							loadData(pageindex);
						} else {
							loadData();
						}
					} else if (e == -2) {
						$.jq_Alert( {
							message : errorMessage
						});
					}
				}
			});
		}
	});
}
