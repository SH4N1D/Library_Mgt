# Generated by Django 3.2.20 on 2024-01-09 10:13

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('libraryapp', '0004_remove_suggestion_suggestion'),
    ]

    operations = [
        migrations.AddField(
            model_name='donation',
            name='status',
            field=models.CharField(default='pending', max_length=20),
            preserve_default=False,
        ),
    ]
