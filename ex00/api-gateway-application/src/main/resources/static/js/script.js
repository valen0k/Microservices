(function umd(root, name, factory) {
    'use strict';
    if ('function' === typeof define && define.amd) {
        define(name, ['jquery'], factory);
    } else {
        root[name] = factory();
    }
}(this, 'ReportOverviewModule', function UMDFactory() {
    'use strict';

    var ReportOverview = ReportOverviewConstructor;

    reportCircleGraph();

    return ReportOverview;

    function ReportOverviewConstructor(options) {

        var factory = {
                init: init
            },

            _elements = {
                $element: options.element
            };

        init();

        return factory;

        function init() {
            _elements.$element.append($(getTemplateString()));
        }

        function getTemplateString() {
            let url = window.location.pathname;


            $.ajax({
                type: 'GET',
                url: url.substring(0, url.length - 9),
                dataType: "json",
                success: function(text) {
                    _elements.$element.empty();
                    _elements.$element.append([
                        '<div>',
                        '<h2>' + text.covid.data.name + ' COVID Overview<span>' + numberWithCommas(text.countries.population) + ' population</span></h2>',
                        '<div class="row">',
                        '<div class="col-md-12">',
                        '<div class="report-statistic-box">',
                        '<div class="box-header">Recovered</div>',
                        '<div class="box-content">',
                        '<div class="recovered">' + numberWithCommas(text.covid.data.latest_data.recovered) + '</div>',
                        '</div>',
                        '</div>',

                        '<div class="report-statistic-box">',
                        '<div class="box-header">Death</div>',
                        '<div class="box-content">',
                        '<div class="death">' + numberWithCommas(text.covid.data.latest_data.deaths) + '</div>',
                        '<div class="box-foot">+' + numberWithCommas(text.covid.data.timeline[Object.keys(text.covid.data.timeline).reverse().pop()].new_deaths) + '</div>',
                        '</div>',
                        '</div>',

                        '<div class="report-statistic-box">',
                        '<div class="box-header">Critical</div>',
                        '<div class="box-content">',
                        '<div class="critical">' + numberWithCommas(text.covid.data.latest_data.critical) + '</div>',
                        '</div>',
                        '</div>',

                        '<div class="report-statistic-box">',
                        '<div class="box-header">Confirmed</div>',
                        '<div class="box-content">',
                        '<div class="confirmed">' + numberWithCommas(text.covid.data.latest_data.confirmed) + '</div>',
                        '<div class="box-foot">+' + numberWithCommas(text.covid.data.timeline[Object.keys(text.covid.data.timeline).reverse().pop()].new_confirmed) + '</div>',
                        '</div>',
                        '</div>',

                        '<div class="report-statistic-box">',
                        '<div class="box-header">Recovery rate</div>',
                        '<div class="box-content recovery-rate">',
                        '<div class="percentage">' + parseInt(text.covid.data.latest_data.calculated.recovery_rate) + '%</div>',
                        '</div>',
                        '</div>',

                        '<div class="report-statistic-box">',
                        '<div class="box-header">Death rate</div>',
                        '<div class="box-content death-rate">',
                        '<div class="percentage">' + parseInt(text.covid.data.latest_data.calculated.death_rate) + '%</div>',
                        '</div>',
                        '</div>',
                    ].join(''));

                    $('.recovery-rate').percentCircle({
                        width: 130,
                        trackColor: '#ececec',
                        barColor: '#81b587',
                        barWeight: 3,
                        endPercent: parseFloat(text.covid.data.latest_data.calculated.recovery_rate) / 100,
                        fps: 80
                    });

                    $('.death-rate').percentCircle({
                        width: 130,
                        trackColor: '#ececec',
                        barColor: '#d78484',
                        barWeight: 3,
                        endPercent: parseFloat(text.covid.data.latest_data.calculated.death_rate) / 100,
                        fps: 80
                    });

                },
                error: function(jqXHR) {
                    console.log('error: ' + jqXHR.status);
                }
            });

            return [
                '<div>',
                '<h2 class="headerload"></h2>',
                '<div class="row">',
                '<div class="col-md-12">',
                '<div class="report-statistic-box h2load"></div>',
                '<div class="report-statistic-box h2load"></div>',
                '<div class="report-statistic-box h2load"></div>',
                '<div class="report-statistic-box h2load"></div>',
                '<div class="report-statistic-box h2load"></div>',
                '<div class="report-statistic-box h2load"></div>',
            ].join('');
        }
    }

    function reportCircleGraph() {

        $.fn.percentCircle = function pie(options) {

            var settings = $.extend({
                width: 130,
                trackColor: '#fff',
                barColor: '#fff',
                barWeight: 5,
                startPercent: 0,
                endPercent: 1,
                fps: 60
            }, options);

            this.css({
                width: settings.width,
                height: settings.width
            });

            var _this = this,
                canvasWidth = settings.width,
                canvasHeight = canvasWidth,
                id = $('canvas').length,
                canvasElement = $('<canvas id="' + id + '" width="' + canvasWidth + '" height="' + canvasHeight + '"></canvas>'),
                canvas = canvasElement.get(0).getContext('2d'),
                centerX = canvasWidth / 2,
                centerY = canvasHeight / 2,
                radius = settings.width / 2 - settings.barWeight / 2,
                counterClockwise = false,
                fps = 1000 / settings.fps,
                update = 0.01;

            this.angle = settings.startPercent;

            this.drawInnerArc = function(startAngle, percentFilled, color) {
                var drawingArc = true;
                canvas.beginPath();
                canvas.arc(centerX, centerY, radius, (Math.PI / 180) * (startAngle * 360 - 90), (Math.PI / 180) * (percentFilled * 360 - 90), counterClockwise);
                canvas.strokeStyle = color;
                canvas.lineWidth = settings.barWeight - 2;
                canvas.stroke();
                drawingArc = false;
            };

            this.drawOuterArc = function(startAngle, percentFilled, color) {
                var drawingArc = true;
                canvas.beginPath();
                canvas.arc(centerX, centerY, radius, (Math.PI / 180) * (startAngle * 360 - 90), (Math.PI / 180) * (percentFilled * 360 - 90), counterClockwise);
                canvas.strokeStyle = color;
                canvas.lineWidth = settings.barWeight;
                canvas.lineCap = 'round';
                canvas.stroke();
                drawingArc = false;
            };

            this.fillChart = function(stop) {
                var loop = setInterval(function() {
                    canvas.clearRect(0, 0, canvasWidth, canvasHeight);

                    _this.drawInnerArc(0, 360, settings.trackColor);
                    _this.drawOuterArc(settings.startPercent, _this.angle, settings.barColor);

                    _this.angle += update;

                    if (_this.angle > stop) {
                        clearInterval(loop);
                    }
                }, fps);
            };

            this.fillChart(settings.endPercent);
            this.append(canvasElement);
            return this;

        };

    }

}));

(function activateReportOverviewModule($) {
    'use strict';

    var $el = $('.report-overview-module');

    return new ReportOverviewModule({
        element: $el
    });
}(jQuery));

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}