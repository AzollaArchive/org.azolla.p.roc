<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<footer class="bs-docs-footer">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div id="roc-id-professional-chart"></div>
                <p>
                    <a href="http://www.miitbeian.gov.cn" target="_blank">沪ICP备12006026号</a>
                    . © ShaneKing
                    . Admin by
                    <a href="/login" target="_self">ShaneKing</a>
                    . Contact me
                    <a target="_blank"
                       href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=7p2Frp2Gj4CLhYeAicCBnIk"
                       style="text-decoration:none;">
                        <img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"/>
                    </a>
                </p>
            </div>
        </div>
    </div>
</footer>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/3th/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/3th/bootstrap/js/bootstrap.min.js"></script>
<script src="/3th/bootcss/js/docs.min.js"></script>
<script src="/3th/prettify/prettify.js"></script>
<script src="/3th/highcharts/js/highcharts.js"></script>
<script>
    $(document).ready(function() {
        prettyPrint();
        var options = {
            chart: {renderTo: 'roc-id-professional-chart', type: 'spline'},
            title: {text: 'Professional Score'},
            xAxis: {type: 'datetime', labels : {formatter : function() {return  Highcharts.dateFormat('%Y-%m-%d', this.value);}}},
            yAxis: {min: 0, title :{text :null}},
            tooltip: {xDateFormat: '%Y-%m-%d %H:%M:%S'},
            plotOptions: {spline: {lineWidth: 1,states: {hover: {lineWidth: 1}},marker: {enabled: false}}},
            credits: {enabled : false},
            series: []
        };
        $.get('${applicationScope.OSS_DOMAIN}roc/professional/lst.csv',function (csvData) {
            var lines = csvData.split('\n');
            var seriesArray = new Array();
            $.each(lines, function(lineNo, line) {
                var items = line.split(',');
                if (lineNo == 0) {
                    $.each(items, function(itemNo, item) {
                        if (itemNo > 0){
                            seriesArray[itemNo] = {data: []};
                            seriesArray[itemNo].name = item;
                        }
                    });
                }else {
                    $.each(items, function(itemNo, item) {
                        if (itemNo > 0){
                            var itemFloat = parseFloat(item);
                            if(!isNaN(itemFloat)){
                                seriesArray[itemNo].data.push(new Array([new Date(items[0]),itemFloat]));
                            }
                        }
                    });
                }
            });
            $.each(seriesArray, function(itemNo, item){
                if (itemNo > 0){
                    options.series.push(item);
                }
            });
            new Highcharts.Chart(options);
        });

    });
</script>