/**
 * 
 */
$(document).ready(function () {
    $('#bookTable').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "dataBook.action",
            "type": "POST"
        },
        "columns": [
            { "data": "ISBN" ,"sTitle":"ISBN", "defaultContent": ""},
            { "data": "Title" ,"sTitle":"标题", "defaultContent": ""},
            { "data": "Author","sTitle":"作者" , "defaultContent": ""},
            { "data": "Publisher","sTitle":"出版社", "defaultContent": "" },
            { "data": "PublishDate","sTitle":"出版日期", "defaultContent": "" },
            { "data": "Price","sTitle":"价格", "defaultContent": "" },
            {"sTitle":"操作","defaultContent": "","bSortable":false}
        ],
        "columnDefs":[
        	{
        		"targets":[6],
        		"data":"ISBN",
        		"render":function(data,type,full){
        			return "<div class='input-group-btn'>"
        			+"<button type='button' class='btn btn-warning btn-xs dropdown-toggle' data-toggle='dropdown'>删除/更新"
                    +"<span class='fa fa-caret-down'></span></button>"
                    +"<ul class='dropdown-menu'>"
                    +"  <li><a href='delBook?ISBN="+data+"'>删除</a></li>"
                    +"  <li><a href='updBookPreLoad?ISBN="+data+"'>更新</a></li>"
                    +"</ul>"
                  +"</div>";
        		}
        	},
        	{
        		"targets":[1],
        		"data":"Title",
        		"render":function(data,type,row,meta){
        			return "<a href='showBook?ISBN="+row.ISBN+"'>"+data +"</a>";
        		}
        	}
        ],
        "oLanguage" : {  
            "sLengthMenu" : "每页显示 _MENU_ 条记录",  
            "sZeroRecords" : "暂时没有数据",  
            "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",  
            "sInfoEmpty" : "没有匹配的数据",  
            "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",  
            "sProcessing" : "努力加载中...",  
            "sSearch" : "<font size=5>搜索作者</font>",  
            "oPaginate" : {  
                "sFirst" : "第一页",  
                "sPrevious" : " 上一页 ",  
                "sNext" : " 下一页 ",  
                "sLast" : " 最后一页 "  
            }  
        },        
        
      'paging'      : true,
      'lengthChange': true,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : true
  })
})
