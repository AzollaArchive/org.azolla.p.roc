var options = {
  chart: {renderTo: 'roc-id-professional-chart', type: 'spline'},
  title: {text: 'Professional Score'},
  xAxis: {
    type: 'datetime', labels: {
      formatter: function () {
        return Highcharts.dateFormat('%Y-%m-%d', this.value);
      }
    }
  },
  yAxis: {min: 0, title: {text: null}},
  tooltip: {xDateFormat: '%Y-%m-%d %H:%M:%S'},
  plotOptions: {spline: {lineWidth: 1, states: {hover: {lineWidth: 1}}, marker: {enabled: false}}},
  credits: {enabled: false},
  series: []
};
$.get('data.csv', function (data) {
  // Split the lines
  var lines = data.split('\n');

  // Iterate over the lines and add categories or series
  $.each(lines, function (lineNo, line) {
    var items = line.split(',');

    // header line containes categories
    if (lineNo == 0) {
      $.each(items, function (itemNo, item) {
        if (itemNo > 0) options.xAxis.categories.push(item);
      });
    }

    // the rest of the lines contain data with their name in the first position
    else {
      var series = {
        data: []
      };
      $.each(items, function (itemNo, item) {
        if (itemNo == 0) {
          series.name = item;
        } else {
          series.data.push(parseFloat(item));
        }
      });

      options.series.push(series);

    }

  });

  // Create the chart
  var chart = new Highcharts.Chart(options);
});
