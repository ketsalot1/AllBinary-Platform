<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

<xsl:import href="/template/generic/customizer/generic/includes/style/advanced/advanced.xsl" />

   <xsl:output method="xml" indent="yes" />

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

         Notes:  The color selector below will set the color for the last color/background-color property you selected.  
         Two cursors and fonts are provided as default for browser compatibility.<br/><br/>

<form method="POST" action="addTemplateStyle.jsp" >

            <xsl:for-each select="STYLES_NAME" >
               <xsl:for-each select="STYLE_NAME" >
                  <xsl:variable name="cssColorNode" select="COLORS_NAME" />

                  <xsl:for-each select="CSS_ELEMENT_NAME" >
                     <table class="wizardtable" >
                     <xsl:call-template name="generateCssStyleElementForm" >
                        <xsl:with-param name="rootCssElementNode" >
                           <xsl:value-of select="current()" />
                        </xsl:with-param>
                        <xsl:with-param name="cssElementNode" >
                           <xsl:value-of select="current()" />
                        </xsl:with-param>
                        <xsl:with-param name="cssColorNode" >
                           <xsl:value-of select="$cssColorNode" />
                        </xsl:with-param>
                     </xsl:call-template>
                     </table>
                     <br/>
                  </xsl:for-each>

               </xsl:for-each>
            </xsl:for-each>


<input class="wizardsubmit" type="submit" value="Continue" 
   name="AdminCommand" />

</form>

         </xsl:for-each>
       </xsl:for-each>

    </xsl:template>

</xsl:stylesheet> 
