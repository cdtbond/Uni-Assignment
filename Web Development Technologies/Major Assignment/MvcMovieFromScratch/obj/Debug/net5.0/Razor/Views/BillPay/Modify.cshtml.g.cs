#pragma checksum "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "46150c3cf5f2a89d4873ba77b4223e007784412d"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_BillPay_Modify), @"mvc.1.0.view", @"/Views/BillPay/Modify.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\_ViewImports.cshtml"
using Assignment2;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\_ViewImports.cshtml"
using DatabaseContext.Models;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\_ViewImports.cshtml"
using Microsoft.AspNetCore.Identity;

#line default
#line hidden
#nullable disable
#nullable restore
#line 1 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
using DatabaseContext.ViewModels;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"46150c3cf5f2a89d4873ba77b4223e007784412d", @"/Views/BillPay/Modify.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"929ce2d2efeadb3c73be40ddd351c8fd8a28cccd", @"/Views/_ViewImports.cshtml")]
    public class Views_BillPay_Modify : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<BillPayViewModel>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("text-danger"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("control-label"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_2 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("form-control"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_3 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "Modify", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        #pragma warning restore 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.ValidationSummaryTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.LabelTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.SelectTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 3 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
  
    ViewData["Title"] = "Modify Bill";
    Layout = "~/Views/Shared/customerlayout.cshtml";

#line default
#line hidden
#nullable disable
            WriteLiteral("\n<h2>Modifying bill in Account ");
#nullable restore
#line 8 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                         Write(Model.AccountNumber);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h2>\n\n<!-- Follows Tutorial 5 approach-->\n<div class=\"row\">\n    <div class=\"col-md-10\">\n        ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d6268", async() => {
                WriteLiteral("\n\n            ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("div", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d6538", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.ValidationSummaryTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper);
#nullable restore
#line 15 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper.ValidationSummary = global::Microsoft.AspNetCore.Mvc.Rendering.ValidationSummary.ModelOnly;

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-validation-summary", __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper.ValidationSummary, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("\n            <div class=\"form-group\">\n                ");
#nullable restore
#line 17 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
           Write(Html.EditorFor(model => model.Amount));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n                ");
#nullable restore
#line 18 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
           Write(Html.ValidationMessageFor(m => m.Amount, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n                <label class=\"text-danger\">");
#nullable restore
#line 19 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                                      Write(Html.ValidationMessageFor(m => m.Amount));

#line default
#line hidden
#nullable disable
                WriteLiteral("</label>\n            </div>\n            <!-- Used this approach to date: https://docs.microsoft.com/en-us/aspnet/mvc/overview/getting-started/introduction/examining-the-edit-methods-and-edit-view -->\n\n            <div class=\"form-group\">\n                ");
#nullable restore
#line 24 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
           Write(Html.EditorFor(model => model.ScheduleDate));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n                ");
#nullable restore
#line 25 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
           Write(Html.ValidationMessageFor(m => m.ScheduleDate, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            </div>\n            <div class=\"form-group\">\n                ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("label", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d10155", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.LabelTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper);
#nullable restore
#line 28 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.Period);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-for", __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_1);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("\n                ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("select", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d11762", async() => {
                    WriteLiteral("\n");
#nullable restore
#line 30 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                     foreach (int id in Enum.GetValues(typeof(BillPayPeriod)))
                    {
                        if (id == (int)Model.Period)
                        {

#line default
#line hidden
#nullable disable
                    WriteLiteral("                            ");
                    __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d12451", async() => {
                        WriteLiteral(" ");
#nullable restore
#line 34 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                                                   Write(Enum.ToObject(typeof(BillPayPeriod), id));

#line default
#line hidden
#nullable disable
                        WriteLiteral(" ");
                    }
                    );
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
                    __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
                    BeginWriteTagHelperAttribute();
#nullable restore
#line 34 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                              WriteLiteral(id);

#line default
#line hidden
#nullable disable
                    __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value = __tagHelperStringValueBuffer;
                    __tagHelperExecutionContext.AddTagHelperAttribute("value", __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                    BeginWriteTagHelperAttribute();
                    __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                    __tagHelperExecutionContext.AddHtmlAttribute("selected", Html.Raw(__tagHelperStringValueBuffer), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.Minimized);
                    await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                    if (!__tagHelperExecutionContext.Output.IsContentModified)
                    {
                        await __tagHelperExecutionContext.SetOutputContentAsync();
                    }
                    Write(__tagHelperExecutionContext.Output);
                    __tagHelperExecutionContext = __tagHelperScopeManager.End();
                    WriteLiteral("\n");
#nullable restore
#line 35 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                        }
                        else
                        {

#line default
#line hidden
#nullable disable
                    WriteLiteral("                            ");
                    __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d15191", async() => {
                        WriteLiteral(" ");
#nullable restore
#line 38 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                                          Write(Enum.ToObject(typeof(BillPayPeriod), id));

#line default
#line hidden
#nullable disable
                        WriteLiteral(" ");
                    }
                    );
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
                    __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
                    BeginWriteTagHelperAttribute();
#nullable restore
#line 38 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                              WriteLiteral(id);

#line default
#line hidden
#nullable disable
                    __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value = __tagHelperStringValueBuffer;
                    __tagHelperExecutionContext.AddTagHelperAttribute("value", __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                    await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                    if (!__tagHelperExecutionContext.Output.IsContentModified)
                    {
                        await __tagHelperExecutionContext.SetOutputContentAsync();
                    }
                    Write(__tagHelperExecutionContext.Output);
                    __tagHelperExecutionContext = __tagHelperScopeManager.End();
                    WriteLiteral("\n");
#nullable restore
#line 39 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                        }
                    }

#line default
#line hidden
#nullable disable
                    WriteLiteral("                ");
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.SelectTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper);
#nullable restore
#line 29 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.Period);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-for", __Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_2);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("\n            </div>\n            <div class=\"form-group\">\n                ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("label", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d18989", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.LabelTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper);
#nullable restore
#line 44 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.Payee);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-for", __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_1);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("\n                ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("select", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d20595", async() => {
                    WriteLiteral("\n");
#nullable restore
#line 46 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                     foreach (Payee payee in Model.Payees)
                    {
                        if (payee.PayeeID == Model.PayeeID)
                        {

#line default
#line hidden
#nullable disable
                    WriteLiteral("                            ");
                    __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d21271", async() => {
                        WriteLiteral(" ");
#nullable restore
#line 50 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                                                              Write(payee.PayeeName);

#line default
#line hidden
#nullable disable
                        WriteLiteral(" ");
                    }
                    );
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
                    __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
                    BeginWriteTagHelperAttribute();
#nullable restore
#line 50 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                              WriteLiteral(payee.PayeeID);

#line default
#line hidden
#nullable disable
                    __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value = __tagHelperStringValueBuffer;
                    __tagHelperExecutionContext.AddTagHelperAttribute("value", __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                    BeginWriteTagHelperAttribute();
                    __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                    __tagHelperExecutionContext.AddHtmlAttribute("selected", Html.Raw(__tagHelperStringValueBuffer), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.Minimized);
                    await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                    if (!__tagHelperExecutionContext.Output.IsContentModified)
                    {
                        await __tagHelperExecutionContext.SetOutputContentAsync();
                    }
                    Write(__tagHelperExecutionContext.Output);
                    __tagHelperExecutionContext = __tagHelperScopeManager.End();
                    WriteLiteral("\n");
#nullable restore
#line 51 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                        }
                        else
                        {

#line default
#line hidden
#nullable disable
                    WriteLiteral("                            ");
                    __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "46150c3cf5f2a89d4873ba77b4223e007784412d24008", async() => {
                        WriteLiteral(" ");
#nullable restore
#line 54 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                                                     Write(payee.PayeeName);

#line default
#line hidden
#nullable disable
                        WriteLiteral(" ");
                    }
                    );
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
                    __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
                    BeginWriteTagHelperAttribute();
#nullable restore
#line 54 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                              WriteLiteral(payee.PayeeID);

#line default
#line hidden
#nullable disable
                    __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                    __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value = __tagHelperStringValueBuffer;
                    __tagHelperExecutionContext.AddTagHelperAttribute("value", __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                    await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                    if (!__tagHelperExecutionContext.Output.IsContentModified)
                    {
                        await __tagHelperExecutionContext.SetOutputContentAsync();
                    }
                    Write(__tagHelperExecutionContext.Output);
                    __tagHelperExecutionContext = __tagHelperScopeManager.End();
                    WriteLiteral("\n");
#nullable restore
#line 55 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
                        }


                    }

#line default
#line hidden
#nullable disable
                    WriteLiteral("                ");
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.SelectTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper);
#nullable restore
#line 45 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.PayeeID);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-for", __Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_2);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("\n            </div>\n            ");
#nullable restore
#line 61 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.HiddenFor(m => m.Account));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            ");
#nullable restore
#line 62 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.HiddenFor(m => m.BillPayID));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            ");
#nullable restore
#line 63 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.HiddenFor(m => m.Customer));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            ");
#nullable restore
#line 64 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.HiddenFor(m => m.PayeeID));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            ");
#nullable restore
#line 65 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.HiddenFor(m => m.AccountNumber));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            ");
#nullable restore
#line 66 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.HiddenFor(m => m.Payees));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n            <button type=\"submit\">Perform Transaction</button>\n            ");
#nullable restore
#line 68 "C:\Users\Christopher\source\repos\Assignment2\MvcMovieFromScratch\Views\BillPay\Modify.cshtml"
       Write(Html.ActionLink("Cancel Transcation", "", new { Controller = "BillPay", Action = "Modify" }, new { @class = "btn btn-primary" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\n        ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Action = (string)__tagHelperAttribute_3.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_3);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\n    </div>\n\n</div>\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public SignInManager<ApplicationUser> signInManager { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<BillPayViewModel> Html { get; private set; }
    }
}
#pragma warning restore 1591
