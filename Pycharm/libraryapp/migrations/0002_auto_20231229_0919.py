# Generated by Django 3.2.20 on 2023-12-29 03:49

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('libraryapp', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='plibrary',
            name='description',
        ),
        migrations.AddField(
            model_name='books',
            name='type',
            field=models.CharField(default=2, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='LIBRARY',
            field=models.ForeignKey(default=2, on_delete=django.db.models.deletion.CASCADE, to='libraryapp.library_table'),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='author',
            field=models.CharField(default=2, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='category',
            field=models.CharField(default=2, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='details',
            field=models.CharField(default=4, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='image',
            field=models.FileField(default=4, upload_to=''),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='review',
            field=models.CharField(default=4, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='donation',
            name='stock',
            field=models.CharField(default=4, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='suggestion',
            name='LIBRARY',
            field=models.ForeignKey(default=4, on_delete=django.db.models.deletion.CASCADE, to='libraryapp.library_table'),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='suggestion',
            name='category',
            field=models.CharField(default=4, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='suggestion',
            name='details',
            field=models.CharField(default=6, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='suggestion',
            name='image',
            field=models.FileField(default=5, upload_to=''),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='suggestion',
            name='review',
            field=models.CharField(default=5, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='suggestion',
            name='stock',
            field=models.CharField(default=6, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='user_table',
            name='profile',
            field=models.FileField(default=7, upload_to=''),
            preserve_default=False,
        ),
        migrations.CreateModel(
            name='shelf_table',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('floor', models.CharField(max_length=100)),
                ('section', models.CharField(max_length=100)),
                ('shelfno', models.CharField(max_length=100)),
                ('BOOK', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='libraryapp.books')),
            ],
        ),
    ]
