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
    pieHole: 0.4
  };

  fetchAppointments(){

    this.graph.getorderstatustotalcount().subscribe((res) =>
  {
    this.appointments = res;
  });
  }
}
