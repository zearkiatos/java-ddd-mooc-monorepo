<#include "partials/header.ftl">
<#include "partials/footer.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
        <title>${title}</title>
        <title>${description}</title>
</head>
<body>
<@header/>
<div class="container mx-auto px-4 p-5">
    <h1 class="font-sans text-gray-800 text-center text-5x1 mb-10">
        <@page_title />
    </h1>
    <@main />
</div>
<div class="clearfix"></div>
<@footer/>
</body>
</html>
