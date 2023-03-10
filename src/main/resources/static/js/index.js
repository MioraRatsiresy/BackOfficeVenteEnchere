/* for line chart */
function drawLineChart(category, series){
	Highcharts.chart('contenu1', {
	    chart: {
	        type: 'line',
	        //width: 500
	    },
	    
	    title: {
	        text: 'Chiffre d\'affaire'
	    },
	
	    xAxis: {
	        categories: category
	    },
	    
	    tooltip: {
	        formatter: function() {
	          return '<strong>'+this.x+': </strong>'+ this.y;
	        }
	    },
	
	    series: [{
	        data: series
	    }]
	});
}


/* for multiple line chart */
function drawMultipleLineChart(formatteddata) {
	Highcharts.chart('container', {

		title: {
			text: 'Solar Employment Growth by Sector, 2010-2019'
		},

		subtitle: {
			text: 'Source: thesolarfoundation.com'
		},

		yAxis: {
			title: {
				text: 'Number of Employees'
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle'
		},

		plotOptions: {
			series: {
				label: {
					connectorAllowed: false
				},
				pointStart: 2011
			}
		},

		series: formatteddata,

		responsive: {
			rules: [{
				condition: {
					maxWidth: 500
				},
				chartOptions: {
					legend: {
						layout: 'horizontal',
						align: 'center',
						verticalAlign: 'bottom'
					}
				}
			}]
		}

	});
}

function viewStatistique() {
	console.log("View statistique");
	const xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			console.log("OK");
			var result = JSON.parse(this.responseText);
			console.log(result["categorie"][0]);
			var series = [];
			var data = [];
			console.log(result);
			for (var i = 0; i < result["categorie"].length; i++) {
				var object = {};
				//console.log("RESULT : " + result[i].categorie);
				object.name = result["categorie"][i];
				object.y = result["nombre"][i];
				data.push(object);
			}
			//console.log("Data="+data[0][name]);
			var seriesObject = {
				name: 'Categorie',
				colorByPoint: true,
				data: data
			};
			series.push(seriesObject);
			console.log(series);
			//document.getElementById("contenu").innerHTML = drawPieChart(series);
			drawPieChart(series);
			//drawLineChart();*/
			var category = result["mois"]
			var series = result["montant"];
			console.log("Mois="+category);
			console.log("Montant="+series);
			drawLineChart(category, series);
		}
	}
	xmlhttp.open("GET", "https://backofficeventeenchere-production-db7d.up.railway.app/statistique");
	xmlhttp.send();
}


/* for pie chart */
function drawPieChart(series) {
	Highcharts.chart('contenu', {
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
			text: 'Nombre de produits vendus pour chaque cat??gorie'
		},
		tooltip: {
			formatter: function () {
				return '<strong>' + this.key + ': </strong>' + this.y;
			}
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.y}'
				}
			}
		},
		series: series
	});
}