/**
 * 
 */
$(document).ready(function () {
    $('#bookTable').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "dataBenzi.action",
            "type": "POST"
        },
        "columns": [
        	{ "sTitle":"Id", "defaultContent": "","visible":false},
        	{ "sTitle":"封面", "defaultContent": "","bSortable":false},
            { "data": "Title" ,"sTitle":"标题", "defaultContent": "","bSortable":true},
            { "data": "Author","sTitle":"作者" , "defaultContent": ""},
            { "data": "Source","sTitle":"资源", "defaultContent": "" },
            { "data": "Publisher","sTitle":"出版社", "defaultContent": "" },
            { "data": "Reference","sTitle":"来源", "defaultContent": "" },
            
            {"sTitle":"操作","defaultContent": "","bSortable":false}
        ],
        "columnDefs":[
        	{
        		"targets":[7],
        		"data":"Id",
        		"render":function(data,type,full){
        			return "<div class='input-group-btn'>"
        			+"<button type='button' class='btn btn-warning btn-xs dropdown-toggle' data-toggle='dropdown'>删除/更新"
                    +"<span class='fa fa-caret-down'></span></button>"
                    +"<ul class='dropdown-menu'>"
                    +"  <li><a href='delBenzi?Id="+data+"'>删除</a></li>"
                    +"  <li><a href='updBenziPreLoad?Id="+data+"'>更新</a></li>"
                    +"</ul>"
                  +"</div>";
        		}
        	},
        	{
        		"targets":[2],
        		"data":"Title",
        		"render":function(data,type,row,meta){
        			return "<a href='showBenzi?Id="+row.Id+"'>"+data +"</a>";
        		}
        	},
        	{
        		"targets":[1],
        		"data":"Cover",
        		"render":function(data,type,row,meta){
        			return "<a class='thumbnail' style='max-width:150px;min-width:100px' href='"+data+"' target='_blank'><img src='"+data+"'/></a>";
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
            "sSearch" : "搜索",  
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
      "order": [[ 0, "desc" ]],
      'info'        : true,
      'autoWidth'   : true
  })
})
