#include "accountdetails.h"
#include "ui_accountdetails.h"

AccountDetails::AccountDetails(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::AccountDetails)
{
    ui->setupUi(this);
}

AccountDetails::~AccountDetails()
{
    delete ui;
}