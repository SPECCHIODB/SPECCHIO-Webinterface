$(document).ready(function () {
    $('.addBtn').on('click', function () {
        //var trID;
        //trID = $(this).closest('tr'); // table row ID 
        addTableRow();
    });
     $('.addBtnRemove').click(function () {
        $(this).closest('tr').remove();  
    })
    var i = 1;
    function addTableRow()
    {
        var tempTr = $('<div class="row">
                       
                        <div class="col-lg-3" id="dropdown_category_' + i + '">
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">All
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">HTML</a></li>
                                    <li><a href="#">CSS</a></li>
                                    <li><a href="#">JavaScript</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- /.col-lg-3 -->
                        <div class="col-lg-3" id="dropdown_attribute_' + i + '>
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">All
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">HTML</a></li>
                                    <li><a href="#">CSS</a></li>
                                    <li><a href="#">JavaScript</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- /.col-lg-3 -->
                        <div class="col-lg-5" id="input_' + i + '>
                            <div class="form-group">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <!-- /.col-lg-5 -->
                        <div class="col-lg-1">
                            <button type="button" class="btn btn-primary addButton"><span class="glyphicon glyphicon-minus addBtnRemove"></span></button>
                        </div>
                        <!-- /.col-lg-1 -->
                       
                       </div>').on('click', function () {
           $(this).closest('tr').remove(); 
           $(document.body).on('click', '.TreatmentHistoryRemove', function (e) {
                $(this).closest('tr').remove();  
            });
        });
        $("#tableAddRow").append(tempTr)
        i++;
    }
});