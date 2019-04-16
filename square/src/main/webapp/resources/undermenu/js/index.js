$.noConflict();
    jQuery('#thumbnail li').click(function ()
    {
        jQuery(this).addClass('active').siblings().removeClass('active');
        var slide = jQuery('#slide li'),
            slideTop = 0,
            slideBlock = jQuery('#slide ul'),
            thum = jQuery('#thumbnail .thumbnail-list li'),
            thumTop = jQuery('#thumbnail .thumbnail-list  .active').position().top - jQuery('#thumbnail .thumbnail-list').position().top + 'px';

        for (var i = 0; i < thum.length; i++)
        {
            if (jQuery(thum[i]).hasClass('active'))
            {
                jQuery(jQuery(slide)[i]).addClass('active').siblings().removeClass('active');
            }
        }


        jQuery('#thumbnail .marker').css('top', (thumTop));
    });
    jQuery(jQuery('#thumbnail li')[0]).trigger('click');