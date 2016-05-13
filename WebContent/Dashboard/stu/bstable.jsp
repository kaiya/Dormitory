<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="container">

		<!-- Modal AddContent -->

		<div class="modal fade" id="AddContentModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">New Student</h4>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Student
									ID:</label> <input type="text" class="form-control" id="addsid">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Student
									Name:</label> <input type="text" class="form-control" id="addsname">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Sex :</label>
								<input type="text" class="form-control" id="addssex">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Building Name
									:</label> <input type="text" class="form-control" id="addbname">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Dormitory Name
									:</label> <input type="text" class="form-control" id="adddname">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Phone
									:</label> <input type="text" class="form-control" id="addsphone">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Class
									Name :</label> <input type="text" class="form-control" id="addsclass">
							</div>
							<div class="form-group">
								<label for="recipient-name" class="control-label">Year :</label>
								<input type="text" class="form-control" id="addsyear">
							</div>

							<div class="form-group">
								<label for="recipient-name" class="control-label">Department
									:</label> <input type="text" class="form-control" id="addsdept">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="saveaddcontent">Save</button>
					</div>
				</div>
			</div>
		</div>
		<div id="toolbar">
			<button id="remove" class="btn btn-danger" disabled>
				<i class="glyphicon glyphicon-remove"></i> Delete
			</button>
			<button id="add" class="btn btn-success">
				<i class="glyphicon glyphicon-plus"></i> Add
			</button>
		</div>
		<table id="table" data-toolbar="#toolbar" data-search="true"
			data-show-refresh="true" data-show-toggle="true"
			data-show-columns="true" data-show-export="true"
			data-detail-view="true" data-detail-formatter="detailFormatter"
			data-minimum-count-columns="2" data-show-pagination-switch="true"
			data-pagination="true" data-id-field="id"
			data-page-list="[10, 25, 50, 100, ALL]" data-show-footer="false"
			data-side-pagination="server" data-url="/Dashboard/ListStu.do"
			data-response-handler="responseHandler">
		</table>
	</div>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/json2/20140204/json2.min.js"></script>
    <![endif]-->

	<script>
		var $table = $('#table'), $remove = $('#remove'), selections = [];
		var $add = $('#add'), $addcontent = $('#addcontent'), $saveaddcontent = $('#saveaddcontent');
		var $AddContentModal = $('#AddContentModal');

		function initTable() {
			$table.bootstrapTable({
				height : getHeight(),
				columns : [ [ {
					field : 'state',
					checkbox : true,
					rowspan : 2,
					align : 'center',
					valign : 'middle'
				}, {
					title : 'ID',
					field : 'sid',
					rowspan : 2,
					align : 'center',
					valign : 'middle',
					sortable : true,
					footerFormatter : totalTextFormatter
				}, {
					title : 'Student Detail',
					colspan : 9,
					align : 'center'
				} ], [ {
					field : 'name',
					title : 'Name',
					sortable : true,
					editable : true,
					footerFormatter : totalNameFormatter,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Name',
					}
				}, {
					field : 'bname',
					title : 'Bname',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Building Name',
					},
					footerFormatter : totalPriceFormatter
				}, {
					field : 'dname',
					title : 'Dname',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Dormitory Name',
					},
					footerFormatter : totalPriceFormatter
				}, {
					field : 'sex',
					title : 'Sex',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Sex',
					},
					footerFormatter : totalPriceFormatter
				},{
					field : 'phone',
					title : 'Phone',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Phone',
					},
					footerFormatter : totalPriceFormatter
				},{
					field : 'className',
					title : 'Class Name',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Class Name',
					},
					footerFormatter : totalPriceFormatter
				},{
					field : 'year',
					title : 'Year',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Year',
					},
					footerFormatter : totalPriceFormatter
				},{
					field : 'department',
					title : 'Dept',
					sortable : true,
					align : 'center',
					editable : {
						type : 'text',
						title : 'Department',
					},
					footerFormatter : totalPriceFormatter
				},
				{
					field : 'operate',
					title : 'Operation',
					align : 'center',
					events : operateEvents,
					formatter : operateFormatter
				} ] ]
			});
			// sometimes footer render error.
			setTimeout(function() {
				$table.bootstrapTable('resetView');
			}, 200);
			$table.on('check.bs.table uncheck.bs.table '
					+ 'check-all.bs.table uncheck-all.bs.table', function() {
				$remove.prop('disabled', !$table
						.bootstrapTable('getSelections').length);

				// save your data, here just save the current page
				selections = getIdSelections();
				// push or splice the selections if you want to save all data selections
			});
			$table.on('expand-row.bs.table', function(e, index, row, $detail) {
				//  if (index % 2 == 1) {
				//      $detail.html('Loading from ajax request...');
				//    $.get('LICENSE', function (res) {
				//      $detail.html(res.replace(/\n/g, '<br>'));
				//  });
				//}
			});
			$table.on('all.bs.table', function(e, name, args) {
				console.log(name, args);
			});
			$remove.click(function() {
				var ids = getIdSelections();
				$table.bootstrapTable('remove', {
					field : 'studentid',
					values : ids
				});
				$remove.prop('disabled', true);
			});
			
			
			<!--插入事件开始-->
			$('#add').on('click', function(e) {
				$AddContentModal.modal('show');
			});

			$saveaddcontent.click(function() {
				//获取输入值
				var addsid = $('#addsid').val();
				var addsname = $('#addsname').val();
				var addssex = $('#addssex').val();
				var addsphone = $('#addsphone').val();
				var addbname = $('#addbname').val();
				var adddname = $('#adddname').val();
				var addsclass = $('#addsclass').val();
				var addsyear = $('#addsyear').val();
				var addsdept = $('#addsdept').val();

				//
				var rowdata = {
					"sid": addsid,
					"name" : addsname,
					"sex" : addssex,
					"studentid" : 100,
					"phone":addsphone,
					"bname":addbname,
					"dname":adddname,
					"className": addsclass,
					"year":addsyear,
					"department" : addsdept
				};
				$table.bootstrapTable('insertRow', {
					index : 100,
					row : rowdata
				});

				//ajax请求保存到数据库

				function GetJSONdata() {
					var json = {
						"action" : "insert",
						"data" : {
							"sid": addsid,
							"name" : addsname,
							"sex" : addssex,
							"phone":addsphone,
							"bname":addbname,
							"dname":adddname,
							"className": addsclass,
							"year":addsyear,
							"department" : addsdept
						},
						"time" : (new Date()).getTime()
					};
					return json;
				}
				$.ajax({
					url : "/Dashboard/EditStu.do",
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify(GetJSONdata()),
					dataType : "json",
					success : function(msg) {
						//关闭modal层 popover
						$AddContentModal.modal('hide');
					},
					error : function(er) {
					}
				});
			});
			
			<!--插入事件结束-->
			
			<!--更新记录开始-->
			$table.on('editable-save.bs.table', function(field, row, oldValue,
					$el) {

				function GetJSON() {
					var json = {
						"action" : "update",
						"row" : row,
						"data" : {
							"studentid" : oldValue.studentid,
							"name" : oldValue.name,
							"sex" : oldValue.sex,
							"phone":oldValue.phone,
							"bname":oldValue.bname,
							"dname":oldValue.dname,
							"className": oldValue.className,
							"year":oldValue.year,
							"department" : oldValue.department
						},
						"time" : (new Date()).getTime()
					};
					return json;
				}
				$.ajax({
					url : "/Dashboard/EditStu.do",
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify(GetJSON()),
					dataType : "json",
					success : function(msg) {

					},
					error : function(er) {
					}
				});
			});
			<!--更新记录结束-->
			
			
			$(window).resize(function() {
				$table.bootstrapTable('resetView', {
					height : getHeight()
				});
			});
		}

		function getIdSelections() {
			return $.map($table.bootstrapTable('getSelections'), function(row) {
				return row.id
			});
		}

		function responseHandler(res) {
			$.each(res.rows, function(i, row) {
				row.state = $.inArray(row.id, selections) !== -1;
			});
			return res;
		}

		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				html.push('<p><b>' + key + ':</b> ' + value + '</p>');
			});
			return html.join('');
		}

		function operateFormatter(value, row, index) {
			return [
					'<a class="remove" href="javascript:void(0)" title="Remove">',
					'<i class="glyphicon glyphicon-remove">_$ta', '_$ta' ]
					.join('');
		}
		
		<!-- 删除事件开始-->
		window.operateEvents = {
				'click .remove' : function(e, value, row, index) {
					var confirm = window.confirm("CONFIRM DELETE?");
					if (confirm) {
						//确定删除！
						//删除事件
						$.ajax({
							url : "/Dashboard/EditStu.do",
							type : "POST",
							contentType : "application/json",
							data : JSON.stringify({
								"action" : "delete",
								"studentid" : row.studentid
							}),
							dataType : "json",
							success : function(msg) {
							},
							error : function(er) {
							}
						});
						$table.bootstrapTable('remove', {
							field : 'studentid',
							values : [ row.studentid ]
						});
					} else {
						//取消删除
					}
					
				}
			};
		<!-- 删除事件结束-->

		function totalTextFormatter(data) {
			return 'Total';
		}

		function totalNameFormatter(data) {
			return data.length;
		}

		function totalPriceFormatter(data) {
			var total = 0;
			$.each(data, function(i, row) {
				total += +(row.price.substring(1));
			});
			return '$' + total;
		}

		function getHeight() {
			return $(window).height() - $('h1').outerHeight(true);
		}

		$(function() {
			var scripts = [
					location.search.substring(1)
							|| '/js/bootstrap-table.min.js',
					'/js/bootstrap-table-export.min.js', '/js/tableExport.js',
					'/js/bootstrap-table-editable.min.js',
					'/js/bootstrap-editable.js' ], eachSeries = function(arr,
					iterator, callback) {
				callback = callback || function() {
				};
				if (!arr.length) {
					return callback();
				}
				var completed = 0;
				var iterate = function() {
					iterator(arr[completed], function(err) {
						if (err) {
							callback(err);
							callback = function() {
							};
						} else {
							completed += 1;
							if (completed >= arr.length) {
								callback(null);
							} else {
								iterate();
							}
						}
					});
				};
				iterate();
			};

			eachSeries(scripts, getScript, initTable);
		});

		function getScript(url, callback) {
			var head = document.getElementsByTagName('body')[0];
			var script = document.createElement('script');
			script.src = url;

			var done = false;
			// Attach handlers for all browsers
			script.onload = script.onreadystatechange = function() {
				if (!done
						&& (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete')) {
					done = true;
					if (callback)
						callback();

					// Handle memory leak in IE
					script.onload = script.onreadystatechange = null;
				}
			};

			head.appendChild(script);

			// We handle everything using the script element injection
			return undefined;
		}
	</script>
</body>
</html>
