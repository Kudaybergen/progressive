$('.newsRow .newsBtn').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $collapse = $this.closest('.collapse-group').find('.collapse');
    $collapse.collapse('toggle');
});
//TODO
//Confirm model
//$('#my_modal').on('show.bs.modal', function(e) {
//    var news = $(e.relatedTarget).data('news');
//    $(e.currentTarget).find('input[name="news"]').val(news);
//});