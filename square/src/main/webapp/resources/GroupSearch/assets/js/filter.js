$('input[type=search]').on({
	  'focus': function(){
	     $(this).parent().addClass('focused');
	   } 
	})

	$('.filter-btn').on('click', function(){
	   $('.filter-btn').removeClass('active');
	   $(this).addClass('active');
	})

	$('.js-close').on('click', function(e){
	  e.preventDefault();
	  $('.wrap-input').removeClass('focused');
	})

	$('input[type=radio]').on('click', function(){
	  var queryField = $('input[type=search]'),
	      queryValue = queryField.val();
	  
	  if( queryValue === '' &&
	      queryValue.search(/:/i) == -1 )
	  {
	       queryField
	        .val( $(this).val() + '' )
	         .focus()
	  }else{  
	       queryField
	         .val( queryValue + '' + $(this).val() + '' ).focus()
	   }
	})