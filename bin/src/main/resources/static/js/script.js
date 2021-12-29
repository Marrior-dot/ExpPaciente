
	var url = '';
	
    $('.visualiza_pdf').click(function() {
		url = $(this).attr('href');
		var $container = $("#pdf");
		PDFObject.embed(url, $container);		
	});
		
	var url_contexto = window.location.href;
	
	if(url_contexto.includes('/documentos/')){
		$( "#link_documentos" ).addClass( "active" );
	}
	else if (url_contexto.includes('/tiposdocumentos/')){
		$( "#link_tiposdocumentos" ).addClass( "active" );
	}
	else if(url_contexto.includes('/artigos/')){
		$( "#link_artigos" ).addClass( "active" );
	}
	else if (url_contexto.includes('/ramais/')){
		$( "#link_ramais" ).addClass( "active" );
		$.ajax({
            "type": 'get',
            "url": '/setores/nodes',
            "dataType": "json",
            "success": function (data) {
                $.each(data, function (idx, obj) {
                    $("#treeTable").append("<tr data-tt-id=\"" + obj.sigla + "\" data-tt-parent-id=\"" + obj.siglaPai + "\"><td>" + obj.sigla + " - " + obj.descricao + " ("+ obj.peso +") " + "</td></tr>");
                });
                $("#treeTable").treetable({
                    expandable: true,
                    clickableNodeNames: true,
                    indent: 20
                });
				$("#treeTable").treetable("expandNode", "PRES");

            }
        });		
	}
	else if (url_contexto.includes('/setores/')){
		$( "#link_setores" ).addClass( "active" );
		
		$.ajax({
            "type": 'get',
            "url": '/setores/nodes',
            "dataType": "json",
            "success": function (data) {
                $.each(data, function (idx, obj) {
                    $("#treeTable").append("<tr data-tt-id=\"" + obj.sigla + "\" data-tt-parent-id=\"" + obj.siglaPai + "\"><td>" + obj.sigla + " - " + obj.descricao + " ("+ obj.peso +") " + "</td></tr>");
                });
                $("#treeTable").treetable({
                    expandable: true,
                    clickableNodeNames: true,
                    indent: 20
                });
				$("#treeTable").treetable("expandNode", "PRES");

            }
        });
		
	}
	else if (url_contexto.includes('/links/')){
		$( "#link_links" ).addClass( "active" );
	}
	
	else if (url_contexto.includes('/tiposlinks/')){
		$( "#link_tiposlinks" ).addClass( "active" );
	}	
	else{
		$( "#link_inicio" ).addClass( "active" );
	}


    $(document).ready(function () {
        
		setTimeout(function(){
			$(".alert").fadeOut("slow", function(){
				$(this).alert("close");
			});			
		}, 5000);
		
        $('#summernote').summernote({        
	        tabsize: 2,
	        height: 120,
			lang: 'pt-BR'
        });
		$('.summernote_conteudo').summernote('code');	
    });

