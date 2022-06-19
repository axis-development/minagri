    
// graph for Quote Vs Contracts --------------------------------------------------------------

var columnChartQuote = function(container, chartcategories , chartData){
   return new Highcharts.Chart({
        credits: false,
        chart: {
            renderTo: container,
            type: 'column'
        },
        title: {
            text: QuotevsContract
        },
        xAxis: {
            categories: [Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec]
        },
        yAxis: {
            min: 0,
            allowDecimals: false,
            title: {
                text: NumberOfQuotesContracts
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.0f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: TotalQuotes,
            data: chartData

        }, {
            name: Contracts,
            data: chartcategories

        },]
    });
};



// graph for Client Info -------------------------------------------------------------

    
    var tableChartClientInfo = function(container, categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
            chart: {
                renderTo: container,
                type: 'column'
            },
          
            title: {
                text: 'Top 10 Cities and top 10 Localities'
            },
            subtitle: {
                text: 'Source: ROM'
            },
            exporting: { 
                enabled: false 
            },

            xAxis: {
                allowDecimals: false,
                type: 'category'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Clients'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y:.0f}'
                    }
                }
            },
            series: [{
                name: 'Clients',
                colorByPoint: false,
                data: data
            }],
                
        });
    }
 
    var pieChartClientInfo = function(container,categories, dataPie){
       return new Highcharts.Chart({
                chart: {
                    renderTo: container,
                    plotBackgroundColor: null,
                    plotBorderWidth: 1,//null,
                    plotShadow: false,
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Client-Type Report Chart'
                },
                /*subtitle: {
                    text: 'Source: ROM'
                },*/
                exporting: {
                    enabled: false 
                },
                tooltip: {
                    pointFormat: '<b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        borderWidth: 0,
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: 'Client-Type',
                    showInLegend: false,
                    data: dataPie
                }],
        });  
    }
    
    
    var columnChartClientInfo = function(container, categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
            chart: {
                renderTo: container,
                type: 'column'
            },
          
                title: {
                    text: 'Client Industry'
                },
                subtitle: {
                    //text: 'Source: ROM'
                },
                exporting: { 
                    enabled: false 
                },
                
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    min: 0,
                    title: {
                        text: 'Clients'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
                
             series: [{
                    name: 'Total',
                    colorByPoint: true,
                    data: data
                }],
                
        });
    }
    
    var barChartClientInfo = function(container, categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
            chart: {
                renderTo: container,
                type: 'bar'
            },
          
                title: {
                    text: 'Form of Contact'
                },
                subtitle: {
                    //text: 'Source: ROM'
                },
                exporting: { 
                    enabled: false 
                },
                
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    min: 0,
                    title: {
                        text: 'Clients'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
                
             series: [{
                    name: 'Total',
                    colorByPoint: true,
                    data: data
                }],
                
        });
    }
    
//-----------------------------------------------------------------------------


// graph for Account Rec Home Screen Info -------------------------------------------------------------
   
   
   
     var lineChartPastDuepayment = function(container, chartcategories , chartData){
       return new Highcharts.Chart({
            chart: {
                renderTo: container,
                type: 'line'
            },
            title: {
                text: 'Quotes Vs Contracts',
                x: -20 //center
            },
            subtitle: {
                //text: 'Source: ROM',
                x: -20
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Count of Quote/Contract'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },

            series: [{
                name: 'Due Payments',
                data: chartData
            }]
        });
    };

    var columnChartAccRec = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                text: 'Stacked column chart'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                allowDecimals: false,
                title: {
                    text: 'Total fruit consumption'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#ded2d2',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y + '<br/>' +
                        'Total: ' + this.point.stackTotal;
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black, 0 0 3px black'
                        }
                    }
                }
            },
            series: data
        }); 
    }
   
// graph for Quote Contract Home Screen Info -------------------------------------------------------------

    
    var lineChartQuoteContract = function(container, chartcategories , chartData){
       return new Highcharts.Chart({
            credits: false,
            chart: {
                renderTo: container,
                type: 'line'
            },
            title: {
                text: 'Quote Vs Contract',
                x: -20 //center
            },
            subtitle: {
                ///text: 'Source: ROM',
                x: -20
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Count of Quote/Contract'
                },
                min: 0,//1
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },

            series: [{
                name: 'Quote',
                data: chartcategories
            }, {
                name: 'Contract',
                data: chartData
            }]
        });
    };
    
    
    var barChartQuoteContract = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'bar'
            },
            title: {
                    text: 'Quotes Payment Mode'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total percent'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Times',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        }); 
    }
    
    var columnChartQuoteContract = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Top 5 Sales Executives'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total Sales'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Sales',
                    colorByPoint: true,
                    data: data,
                    pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        }); 
    }
    
    
    var tableChartQuoteContract = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Top 10 Products'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total Sales'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Quantity',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        });
    }
//Account Receivble--------------------------------------------------------------------------

     var lineChartAccRecPastDuepayment = function(container, chartcategories , chartData){
       return new Highcharts.Chart({
            chart: {
                renderTo: container,
                type: 'line'
            },
            title: {
                text: 'Past Due Payments',
                x: -20 //center
            },
            subtitle: {
                //text: 'Source: ROM',
                x: -20
            },
            credits: {
                enabled: false
              },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Total Amount'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }],
                min: 0
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },

            series: [{
                name: 'Due Payments',
                data: chartData
            }]
        });
    }

    var columnChartAccRec = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column',
                marginTop: 80
            },
            
            title: {
                text: 'Unpaid Contracts and Credit Notes'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                allowDecimals: false,
                title: {
                    text: 'Total Amount'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#ded2d2',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y + '<br/>' +
                        'Total: ' + this.point.stackTotal;
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black, 0 0 3px black'
                        }
                    }
                }
            },
            series: data
        }); 
    }
// Requisition ------------------------------------------------------------------------------

      var columnChartReqPaid = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Paid to Suppliers'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    min:0,
                    allowDecimals: false,
                    title: {
                        text: 'Total Amount'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Amount',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        }); 
    }
    
    var tableChartTopExp = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Top 5 Expenses by (Department and Category)'
                },
            xAxis: {
                type: 'category'
            },
            yAxis: {
                min:0,
                allowDecimals: false,
                title: {
                    text: 'Total Amount'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
            series: [{
                name: 'Amount',
                colorByPoint: false,
                data: data
            }],
        }); 
    }
    
    
//  Order code-----------------------------------

    var ColumnChartOrderStatus = function(container,categories, data){
        return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Order Status'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    min:0,
                    title: {
                        text: 'Total'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Orders',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        }); 
    }
    
    var tableChartTopSupplier = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Top 10 Suppliers'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    min:0,
                    allowDecimals: false,
                    title: {
                        text: 'Total Amount'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Amount',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        });
    }
    var tableChartQuoteContractPipe = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Quote & Contract Pipeline'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Total',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        });
    }
    var columnChartPaymentPipe = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Account Receivable'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Clients',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        });
    }
    var columnChartDueNormalPipe = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Account Payable'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Clients',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        });
    }
    var columnChartOrderPipe = function(container,categories, data){
       return new Highcharts.Chart({
            credits: {
                enabled: false
            },
             chart: {
                renderTo: container,
                type: 'column'
            },
            title: {
                    text: 'Order to Stock'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Total'
                    }
                },
                legend: {
                    enabled: false
                },
            plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.0f}'
                        }
                    }
                },
             series: [{
                    name: 'Clients',
                    colorByPoint: true,
                    data: data,
                    //pointWidth: 28
                }],
                drilldown: {
                    series: categories
                }
        });
    }