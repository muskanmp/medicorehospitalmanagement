export class Appointment {
    id!: number;
    patient!: Patient;
    doctor!: Doctor;
    department!: Department;
    appointmenttime!: string;
    status!: string;
    reason!: string;
}

export class Patient {
    id!: number;
    name!: string;
    age!: number;
    gender!: string;
    contactNumber!: string;
    email!: string;
    address!: string;
    bloodGroup!: string;
}

export class Doctor {
    id!: number ;
    name!: string;
    specialization!: string;
    email!: string;
}

export class Department {
    id!: number;
    name!: string;
}

export class Stats{
    totalPatients!: number;
    totalDoctors!: number;
    totalAppointments!: number;
    totalDepartments!: number;
}