#include "login.h"
#include "ui_login.h"

Login::Login(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Login)
{
    ui->setupUi(this);
}

Login::~Login()
{
    delete ui;
}

void Login::on_logButton_clicked()
{
 QString email = ui->emailLog->text();
 QString password = ui->passLog->text();

 //check from the DB if correct go to home if not QMessageBox warning

}


void Login::on_regButton_clicked()
{
 hide();
}
