import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ChartType } from 'angular-google-charts';
import { WebClientService } from '../web-client-service';
import { GraphService } from '../graph.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent
{


  constructor(private graph: GraphService, protected router: Router, private webclient: WebClientService) { }

  type = 'PieChart';
  appointments: any[] = [];
  stats: any;
  doctorStats: any[][] = [];

  data = [
    ['Patients', 120],
    ['Doctors', 15],
    ['Appointments', 300],
    ['Departments', 6]
  ];

  columnNames = ['Module', 'Count'];

  pieChartType: ChartType = ChartType.PieChart;
  column: ChartType = ChartType.ColumnChart;
  bar: ChartType = ChartType.BarChart;
  doughnut: ChartType = ChartType.PieChart;
  LineChart: ChartType = ChartType.LineChart;
  combo = ChartType.ComboChart;

  options = {
    pieHole: 0.4,
       colors: [
      '#f0a8af', '#EC407A', '#052c65', '#c2c2f0', '#0069d9', '#bf86e7',
      '#dc3545', '#ff7700', '#66ff66', '#c2c2f0', '#AB47BC', '#ffcd36',
      '#052c65', '#7E57C2', '#70b595', '#FFCA28', '#FF6384', '#36A2EB', '#66ff66',
    ]
  };

  
  ngOnInit()
  {
    this.fetchStats();
  }

  fetchStats(){

    this.graph.getDashboardStatsTotalCount().subscribe((res) =>
  {
    this.stats = res;
  });
  }

  fetchDoctorStats(){

    this.graph.getDoctorStats().subscribe((res) =>
  {
    this.doctorStats = res;
  });
  }
}
