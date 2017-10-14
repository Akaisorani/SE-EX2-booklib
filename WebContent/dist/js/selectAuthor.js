/**
 * 
 */
$(document).ready(function () {
    $('#selectAuthor').select2({
    	placeholder:'请选择作者',
    	ajax:{
    		url:'selectAuthor.action',
    		dataType:'json',
    		delay:250,
    		data:function(params){
    			return{
    				q:params.term,
    				page:params.page,
    				length:20
    			};
    		},
    		processResults: function (data,params){
    			params.page=params.page||1;
    			return{
    				results:data.items,
    				pagination:{
    					more:(params.page*20)<data.total_count
    				}
    			};
    		},
    		cache:true
    	},
    	allowClear:true,
    	escapeMarkup:function(markup){return markup;},
    	minimumInputLength:0,
    	templateResult:formatRepo,
    	templateSelection:formatRepoSelection,
    });
});
function formatRepo(repo){
	if(repo.loading){
		return repo.text;
	}
	return repo.text+"<i style='color:gray'> "+repo.Age+"岁 "+repo.Country+"</i>";
}
function formatRepoSelection(repo){
	return repo.text;
}