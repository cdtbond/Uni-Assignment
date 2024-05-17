#pragma checksum "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "f879a62baec6685e3fbb8a3156c0cddaaebaef7c"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Courses_Create), @"mvc.1.0.view", @"/Views/Courses/Create.cshtml")]
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
#line 1 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\_ViewImports.cshtml"
using CourseEnrollment;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\_ViewImports.cshtml"
using CourseEnrollment.Models;

#line default
#line hidden
#nullable disable
#nullable restore
#line 1 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
using CourseEnrollment.ViewModel;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
using CourseEnrollment.Data;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"f879a62baec6685e3fbb8a3156c0cddaaebaef7c", @"/Views/Courses/Create.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"7c99554cb88a1ad610a174451e6b2f32f33a9605", @"/Views/_ViewImports.cshtml")]
    public class Views_Courses_Create : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<CreateCourseViewModel>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("text-danger"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("control-label"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_2 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("form-control"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_3 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "Create", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
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
#line 4 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
  
    ViewData["Title"] = "Add Student";

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n<!-- Used to create new students for ease of demonstration-->\r\n\r\n<h1>Add Course</h1>\r\n\r\n");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "f879a62baec6685e3fbb8a3156c0cddaaebaef7c5937", async() => {
                WriteLiteral("\r\n    ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("div", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "f879a62baec6685e3fbb8a3156c0cddaaebaef7c6199", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.ValidationSummaryTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationSummaryTagHelper);
#nullable restore
#line 13 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
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
                WriteLiteral("\r\n\r\n    <div class=\"form-group\">\r\n        <label class=\"control-label\">Course ID</label> <br />\r\n        ");
#nullable restore
#line 17 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.EditorFor(model => model.CourseID));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n        ");
#nullable restore
#line 18 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.ValidationMessageFor(m => m.CourseID, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n    </div>\r\n    <div class=\"form-group\">\r\n        <label class=\"control-label\">Title</label><br />\r\n        ");
#nullable restore
#line 22 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.EditorFor(model => model.Title));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n        ");
#nullable restore
#line 23 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.ValidationMessageFor(m => m.Title, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n    </div>\r\n    <div class=\"form-group\">\r\n        <label class=\"control-label\">Credit Points Allocated</label><br />\r\n        ");
#nullable restore
#line 27 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.EditorFor(model => model.CreditPoints));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n        ");
#nullable restore
#line 28 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.ValidationMessageFor(m => m.CreditPoints, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n    </div>\r\n    <div class=\"form-group\">\r\n        ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("label", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "f879a62baec6685e3fbb8a3156c0cddaaebaef7c10056", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.LabelTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper);
#nullable restore
#line 31 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_LabelTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.Career);

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
                WriteLiteral("\r\n        <!-- Must be a dropdown -->\r\n        ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("select", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "f879a62baec6685e3fbb8a3156c0cddaaebaef7c11698", async() => {
                    WriteLiteral("\r\n            ");
                    __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "f879a62baec6685e3fbb8a3156c0cddaaebaef7c11979", async() => {
                        WriteLiteral(" ");
#nullable restore
#line 34 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
                                            Write(Career.Undergraduate);

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
#line 34 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
              WriteLiteral(Career.Undergraduate);

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
                    WriteLiteral("\r\n            ");
                    __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "f879a62baec6685e3fbb8a3156c0cddaaebaef7c14054", async() => {
                        WriteLiteral(" ");
#nullable restore
#line 35 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
                                           Write(Career.Postgraduate);

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
#line 35 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
              WriteLiteral(Career.Postgraduate);

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
                    WriteLiteral("\r\n        ");
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.SelectTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper);
#nullable restore
#line 33 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_SelectTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.Career);

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
                WriteLiteral("\r\n        ");
#nullable restore
#line 37 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.ValidationMessageFor(m => m.Career, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n    </div>\r\n    <div class=\"form-group\">\r\n        <label class=\"control-label\">Course Coordinator</label><br />\r\n        ");
#nullable restore
#line 41 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.EditorFor(model => model.Coordinator));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n        ");
#nullable restore
#line 42 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
   Write(Html.ValidationMessageFor(m => m.Coordinator, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n    </div>\r\n\r\n    <button type=\"submit\">Create Course</button>\r\n    ");
#nullable restore
#line 46 "C:\Users\Christopher\source\repos\CourseEnrollment\CourseEnrollment\Views\Courses\Create.cshtml"
Write(Html.ActionLink("Cancel", "", new { Controller = "Students", Action = "Create" }, new { @class = "btn btn-primary" }));

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n");
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
            WriteLiteral("\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<CreateCourseViewModel> Html { get; private set; }
    }
}
#pragma warning restore 1591
